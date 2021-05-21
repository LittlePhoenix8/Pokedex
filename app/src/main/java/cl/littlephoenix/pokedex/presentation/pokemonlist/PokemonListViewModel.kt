package cl.littlephoenix.pokedex.presentation.pokemonlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.littlephoenix.pokedex.data.model.PokemonResponse
import cl.littlephoenix.pokedex.repository.PokedexRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokedexRepository: PokedexRepository
) : ViewModel() {
    val pokemonList : MutableLiveData<PokemonResponse> by lazy {
        MutableLiveData<PokemonResponse>()
    }

    val errorMessage : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getFirstGenPokemon() {
        viewModelScope.launch {
            val response = pokedexRepository.getFirstGenPokemon()
            response?.let {
                if (it.isSuccessful) {
                    val pokeList = it.body()
                    if (pokeList == null) {
                        errorMessage.postValue("There are no pokemon to show right now, please try again")
                    } else {
                        pokemonList.postValue(pokeList)
                    }
                } else {
                    Log.e("Error", it.message())
                    errorMessage.postValue("Ups, there was an error, please try again")
                }
            }?: run {
                Log.e("Error", "Error")
                errorMessage.postValue("Ups, there was an error, please try again")
            }
        }
    }
}