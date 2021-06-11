package cl.littlephoenix.pokedex.presentation.pokemonlist

import android.util.Log
import androidx.lifecycle.*
import cl.littlephoenix.pokedex.data.model.toEntity
import cl.littlephoenix.pokedex.data.model.toModel
import cl.littlephoenix.pokedex.data.repository.PokedexLocalRepository
import cl.littlephoenix.pokedex.data.repository.PokedexRepository
import cl.littlephoenix.pokedex.presentation.model.toModel
import cl.littlephoenix.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokedexRepository: PokedexRepository,
    private val localRepository: PokedexLocalRepository) : ViewModel() {
    fun getFirstGenPokemon() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val local = localRepository.getAllPokemon()
        if (local.isNotEmpty()) {
            val pokemonModelList = local.map { it.toModel() }
            emit(Resource.success(pokemonModelList))
        }
        try {
            val remote = pokedexRepository.getFirstGenPokemon()
            if (remote.isSuccessful) {
                if (remote.body() != null) {
                    val pokemonDbList = remote.body()!!.results.map { it.toEntity() }
                    val pokemonModelList = remote.body()!!.results.map { it.toModel() }
                    localRepository.saveAllPokemon(pokemonDbList)
                    emit(Resource.success(pokemonModelList))
                } else {
                    emit(Resource.error("There are no pokemon to show right now, please try again", null))
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

    fun getTypes() = liveData(Dispatchers.IO) {
        try {
            val remote = pokedexRepository.getTypes()
            if (remote.isSuccessful) {
                if (remote.body() != null) {
                    val body = remote.body()!!
                    localRepository.saveTypes(body.toEntity())
                    emit(Resource.success(body))
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