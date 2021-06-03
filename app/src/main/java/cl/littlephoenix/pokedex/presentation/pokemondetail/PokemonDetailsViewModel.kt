package cl.littlephoenix.pokedex.presentation.pokemondetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import cl.littlephoenix.pokedex.data.model.toEntity
import cl.littlephoenix.pokedex.data.model.toModel
import cl.littlephoenix.pokedex.data.model.toPokeTypeEntity
import cl.littlephoenix.pokedex.data.repository.PokedexLocalRepository
import cl.littlephoenix.pokedex.data.repository.PokedexRepository
import cl.littlephoenix.pokedex.presentation.model.PokemonModel
import cl.littlephoenix.pokedex.presentation.model.toEntity
import cl.littlephoenix.pokedex.presentation.model.toEvolutionEntity
import cl.littlephoenix.pokedex.presentation.model.toModel
import cl.littlephoenix.pokedex.utils.Resource
import cl.littlephoenix.pokedex.utils.getIdFromUrl
import cl.littlephoenix.pokedex.utils.getNameUppercase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokedexRepository: PokedexRepository,
    private val localRepository: PokedexLocalRepository) : ViewModel() {
        fun getPokemonDetails(pokemonId: Int) = liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val local = localRepository.getPokemon(pokemonId)
            val attacks = localRepository.getAttacksByPokemon(pokemonId)
            val skills = localRepository.getSkillsByPokemon(pokemonId)
            val pokeType = localRepository.getPokeTypesByPokemon(pokemonId)
            val types = localRepository.getTypesById(pokeType.map { it.typeId })
            val evols = localRepository.getPokemonEvolutions(pokemonId)
            Log.d("DetailLocal", Gson().toJson(local))
            if (local != null && attacks.isNotEmpty() && skills.isNotEmpty() && types.isNotEmpty()) {
                val modelLocal = local.toModel()
                modelLocal.type = types.map { it.type }
                modelLocal.attacks = attacks.map { it.attack }
                modelLocal.skills = skills.map { it.skill }
                modelLocal.evolutions = evols.map { it.toModel() }
                emit(Resource.success(modelLocal))
            }
            try {
                val remote = pokedexRepository.getPokemonDetail(pokemonId)
                val remoteSpecie = pokedexRepository.getPokemonSpecie(pokemonId)
                var chainId = -1
                val evolutionList = ArrayList<PokemonModel>()
                if (remoteSpecie.body() == null) {
                    Log.e("NetworkError", "network error")
                    emit(Resource.error("Ups, there was an error, please try again", null))
                } else {
                    chainId = remoteSpecie.body()!!.evolution_chain.url.getIdFromUrl()
                    val evolutions = pokedexRepository.getPokemonEvolutions(chainId)
                    Log.d("Evolutions", Gson().toJson(evolutions))
                    if (evolutions.isSuccessful && evolutions.body() != null) {
                        if (evolutions.body()!!.chain.evolves_to.isNotEmpty()) {
                            evolutionList.add(evolutions.body()!!.chain.species.toModel(chainId))
                            for (poke in evolutions.body()!!.chain.evolves_to) {
                                evolutionList.add(poke.species.toModel(chainId))
                                if (poke.evolves_to.isNotEmpty()) {
                                    for (p in poke.evolves_to) {
                                        evolutionList.add(p.species.toModel(chainId))
                                    }
                                }
                            }
                        }
                    }
                    Log.d("EvolutionsToModel", Gson().toJson(evolutionList))
                    Log.d("EvolutionsSize", evolutionList.size.toString())
                    val evolutionEntity = evolutionList.map { it.toEvolutionEntity(pokemonId) }
                    localRepository.savePokemonEvolutions(evolutionEntity)
                }
                if (remote.isSuccessful) {
                    if (remote.body() != null) {
                        Log.d("DetailRemote", Gson().toJson(remote.body()))
                        val pokemonModel = remote.body()!!.toModel(chainId)
                        pokemonModel.evolutions = evolutionList
                        val pokemonEntity = pokemonModel.toEntity()
                        val attacksEntity = remote.body()!!.moves.map { it.move.toEntity(pokemonId) }
                        val skillsEntity = remote.body()!!.abilities.map { it.ability.toEntity(pokemonId) }
                        val pokeTypeEntity = remote.body()!!.types.map { it.type.toPokeTypeEntity(pokemonId) }
                        localRepository.updateAllPokemon(listOf(pokemonEntity))
                        localRepository.saveAttacks(attacksEntity)
                        localRepository.saveSkills(skillsEntity)
                        localRepository.savePokeTypes(pokeTypeEntity)
                        emit(Resource.success(pokemonModel))
                    } else {
                        Log.e("NetworkError", "network error")
                        emit(Resource.error("Ups, there was an error, please try again", null))
                    }
                } else {
                    Log.e("NetworkError", "network error")
                    emit(Resource.error("Ups, there was an error, please try again", null))
                }
            } catch (e: Exception) {
                Log.e("Ex", e.message, e)
                emit(Resource.error("Ups, there was an error, please try again", null))
            }
        }

    fun getPokemonEncounters(pokemonId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val local = localRepository.getLocationsByPokemon(pokemonId)
        Log.d("LocationLocal", Gson().toJson(local))
        if (local != null) {
            emit(Resource.success(local.map { it.location }))
        }
        try {
            val remote = pokedexRepository.getPokemonLocation(pokemonId)
            if (remote.isSuccessful) {
                if (remote.body() != null) {
                    Log.d("LocationRemote", Gson().toJson(remote))
                    val locations = remote.body()!!.map { it.location_area.name.getNameUppercase() }
                    val locationsEntity = remote.body()!!.map { it.location_area.toEntity(pokemonId) }
                    localRepository.saveLocations(locationsEntity)
                    emit(Resource.success(locations))
                } else {
                    Log.e("NetworkError", "network error")
                    emit(Resource.error("Ups, there was an error, please try again", null))
                }
            } else {
                Log.e("NetworkError", "network error")
                emit(Resource.error("Ups, there was an error, please try again", null))
            }
        } catch (e: Exception) {
            Log.e("Ex", e.message, e)
            emit(Resource.error("Ups, there was an error, please try again", null))
        }
    }
}