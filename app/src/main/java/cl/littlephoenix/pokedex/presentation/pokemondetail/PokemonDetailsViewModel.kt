package cl.littlephoenix.pokedex.presentation.pokemondetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import cl.littlephoenix.pokedex.data.model.toModel
import cl.littlephoenix.pokedex.data.repository.PokedexLocalRepository
import cl.littlephoenix.pokedex.data.repository.PokedexRepository
import cl.littlephoenix.pokedex.presentation.model.PokemonModel
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
            //TODO check details local
            try {
                val remote = pokedexRepository.getPokemonDetail(pokemonId)
                if (remote == null) {
                    Log.e("NetworkError", "network error")
                    emit(Resource.error("Ups, there was an error, please try again", null))
                } else {
                    Log.d("Remote", Gson().toJson(remote))
                    //TODO update database
                    val pokemonModel = remote.toModel()
                    Log.d("ToModel", Gson().toJson(pokemonModel))
                    emit(Resource.success(pokemonModel))
                }
            } catch (e: Exception) {
                Log.e("Ex", e.message, e)
                emit(Resource.error("Ups, there was an error, please try again", null))
            }
        }

    fun getPokemonEncounters(pokemonId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        //TODO check details local
        try {
            val remote = pokedexRepository.getPokemonLocation(pokemonId)
            if (remote == null) {
                Log.e("NetworkError", "network error")
                emit(Resource.error("Ups, there was an error, please try again", null))
            } else {
                Log.d("Remote", Gson().toJson(remote))
                //TODO update database
                val locations = remote.map { it.location_area.name.getNameUppercase().replace("-", " ") }
                emit(Resource.success(locations))
            }
        } catch (e: Exception) {
            Log.e("Ex", e.message, e)
            emit(Resource.error("Ups, there was an error, please try again", null))
        }
    }

    fun getPokemonSpecies(pokemonId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        //TODO check details local
        try {
            val remote = pokedexRepository.getPokemonSpecie(pokemonId)
            if (remote == null) {
                Log.e("NetworkError", "network error")
                emit(Resource.error("Ups, there was an error, please try again", null))
            } else {
                Log.d("Remote", Gson().toJson(remote))
                //TODO update database
                val chainId = remote.evolution_chain.url.getIdFromUrl()
                Log.d("ChainId", chainId.toString())
                try {
                    val evolutions = pokedexRepository.getPokemonEvolutions(chainId)
                    Log.d("Evolutions", Gson().toJson(evolutions))
                    //TODO update database
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