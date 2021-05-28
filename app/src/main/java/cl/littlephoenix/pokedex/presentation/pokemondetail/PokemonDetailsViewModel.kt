package cl.littlephoenix.pokedex.presentation.pokemondetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import cl.littlephoenix.pokedex.data.model.toEntity
import cl.littlephoenix.pokedex.data.model.toModel
import cl.littlephoenix.pokedex.data.repository.PokedexLocalRepository
import cl.littlephoenix.pokedex.data.repository.PokedexRepository
import cl.littlephoenix.pokedex.presentation.model.PokemonModel
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
            Log.d("LocalD", Gson().toJson(local))
            Log.d("attacks D", Gson().toJson(attacks))
            Log.d("skills D", Gson().toJson(skills))
            if (local != null && attacks != null && skills != null) {
                //TODO get type
                val modelLocal = local.toModel()
                modelLocal.attacks = attacks.map { it.attack }
                modelLocal.skills = skills.map { it.skill }
                emit(Resource.success(modelLocal))
            }
            try {
                val remote = pokedexRepository.getPokemonDetail(pokemonId)
                if (remote == null) {
                    Log.e("NetworkError", "network error")
                    emit(Resource.error("Ups, there was an error, please try again", null))
                } else {
                    Log.d("RemoteD", Gson().toJson(remote))
                    val pokemonModel = remote.toModel()
                    val pokemonEntity = remote.toEntity()
                    Log.d("ToModelD", Gson().toJson(pokemonModel))
                    Log.d("toEntityD", Gson().toJson(pokemonEntity))

                    //TODO save type
                    val attacksEntity = remote.moves.map { it.move.toEntity(pokemonId) }
                    val skillsEntity = remote.abilities.map { it.ability.toEntity(pokemonId) }
                    Log.d("attacksEntity", Gson().toJson(attacksEntity))
                    Log.d("skillsEntity", Gson().toJson(skillsEntity))

                    localRepository.updateAllPokemon(listOf(pokemonEntity))
                    localRepository.saveAttacks(attacksEntity)
                    localRepository.saveSkills(skillsEntity)
                    emit(Resource.success(pokemonModel))
                }
            } catch (e: Exception) {
                Log.e("Ex", e.message, e)
                emit(Resource.error("Ups, there was an error, please try again", null))
            }
        }

    fun getPokemonEncounters(pokemonId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val local = localRepository.getLocationsByPokemon(pokemonId)
        Log.d("Location D", Gson().toJson(local))
        if (local != null) {
            emit(Resource.success(local.map { it.location }))
        }
        try {
            val remote = pokedexRepository.getPokemonLocation(pokemonId)
            if (remote == null) {
                Log.e("NetworkError", "network error")
                emit(Resource.error("Ups, there was an error, please try again", null))
            } else {
                Log.d("Remote", Gson().toJson(remote))
                val locations = remote.map { it.location_area.name.getNameUppercase().replace("-", " ") }
                val locationsEntity = remote.map { it.location_area.toEntity(pokemonId) }
                Log.d("locationsEntity", Gson().toJson(locationsEntity))
                localRepository.saveLocations(locationsEntity)
                emit(Resource.success(locations))
            }
        } catch (e: Exception) {
            Log.e("Ex", e.message, e)
            emit(Resource.error("Ups, there was an error, please try again", null))
        }
    }

    fun getPokemonSpecies(pokemonId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        //TODO check evolutions local
        try {
            val remote = pokedexRepository.getPokemonSpecie(pokemonId)
            if (remote == null) {
                Log.e("NetworkError", "network error")
                emit(Resource.error("Ups, there was an error, please try again", null))
            } else {
                Log.d("Remote", Gson().toJson(remote))
                //TODO update evolutions database
                val chainId = remote.evolution_chain.url.getIdFromUrl()
                Log.d("ChainId", chainId.toString())
                try {
                    val evolutions = pokedexRepository.getPokemonEvolutions(chainId)
                    Log.d("Evolutions", Gson().toJson(evolutions))
                    //TODO update evolutions database
                    val result = ArrayList<PokemonModel>()
                    if (evolutions != null) {
                        if (evolutions.chain.evolves_to.isNotEmpty()) {
                            result.add(evolutions.chain.species.toModel(chainId))
                            for (poke in evolutions.chain.evolves_to) {
                                result.add(poke.species.toModel(chainId))
                                if (poke.evolves_to.isNotEmpty()) {
                                    for (p in poke.evolves_to) {
                                        result.add(p.species.toModel(chainId))
                                    }
                                }
                            }
                        }
                    }
                    Log.d("ToModel", Gson().toJson(result))
                    emit(Resource.success(result))
                } catch (e: Exception) {
                    Log.e("Ex", e.message, e)
                    emit(Resource.error("Ups, there was an error, please try again", null))
                }
            }
        } catch (e: Exception) {
            Log.e("Ex", e.message, e)
            emit(Resource.error("Ups, there was an error, please try again", null))
        }
    }
}