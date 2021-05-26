package cl.littlephoenix.pokedex.presentation.pokemondetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import cl.littlephoenix.pokedex.data.repository.PokedexLocalRepository
import cl.littlephoenix.pokedex.data.repository.PokedexRepository
import cl.littlephoenix.pokedex.utils.Resource
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
                    //TODO parse to model
                    val pokemonModel = remote
                    Log.d("ToModel", Gson().toJson(pokemonModel))
                    emit(Resource.success(pokemonModel))
                }
            } catch (e: Exception) {
                Log.e("Ex", e.message, e)
                emit(Resource.error("Ups, there was an error, please try again", null))
            }
        }
}