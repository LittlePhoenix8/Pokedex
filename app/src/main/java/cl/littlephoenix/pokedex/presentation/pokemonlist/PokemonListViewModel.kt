package cl.littlephoenix.pokedex.presentation.pokemonlist

import androidx.lifecycle.*
import cl.littlephoenix.pokedex.data.repository.PokedexRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokedexRepository: PokedexRepository) : ViewModel() {
    /*val pokemonList : MutableLiveData<PokemonResponse> by lazy {
        MutableLiveData<PokemonResponse>()
    }
    val errorMessage : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }*/
    val pokemonList = pokedexRepository.getFirstGenPokemon()

    /*fun getFirstGenPokemon() {
        viewModelScope.launch {
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
    }*/
}