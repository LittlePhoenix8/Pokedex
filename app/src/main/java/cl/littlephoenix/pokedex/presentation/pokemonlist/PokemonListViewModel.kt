package cl.littlephoenix.pokedex.presentation.pokemonlist

import android.util.Log
import androidx.lifecycle.*
import cl.littlephoenix.pokedex.data.entities.PokemonEntity
import cl.littlephoenix.pokedex.data.model.PokemonResponse
import cl.littlephoenix.pokedex.data.model.toEntity
import cl.littlephoenix.pokedex.data.model.toModel
import cl.littlephoenix.pokedex.data.repository.PokedexLocalRepository
import cl.littlephoenix.pokedex.data.repository.PokedexRepository
import cl.littlephoenix.pokedex.data.repository.PokedexRepositoryRx
import cl.littlephoenix.pokedex.presentation.model.PokemonModel
import cl.littlephoenix.pokedex.presentation.model.toModel
import cl.littlephoenix.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import java.util.function.Consumer
import java.util.function.Function
import javax.inject.Inject


@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokedexRepository: PokedexRepository,
    private val pokedexRepositoryRx: PokedexRepositoryRx,
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

    private val pokemonSecondGenerationList = MutableLiveData<List<PokemonModel>>()
    private val pokemonSecondGenerationError = MutableLiveData<String>()

    fun getPokemonSecondGenerationList(): MutableLiveData<List<PokemonModel>> = pokemonSecondGenerationList
    fun getPokemonSecondGenerationError(): MutableLiveData<String> = pokemonSecondGenerationError

    fun getSecondGenerationPokemon() {
        pokedexRepositoryRx.getSecondGenPokemon()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.results.map { poke -> poke.toModel() }
            }
            .subscribe(
                { pokemonEntityList -> pokemonSecondGenerationList.value = pokemonEntityList },
                { error -> pokemonSecondGenerationError.value = error.message }
            )
    }

    fun saveSecondGeneration(pokemon: List<PokemonEntity>) {
        localRepository.saveSecondGen(pokemon)
            .subscribeOn(Schedulers.io())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable?) {
                    Log.d("onSubscribe", "isDisposed ${d?.isDisposed}")
                }
                override fun onComplete() {
                    Log.d("onComplete", "ok")
                }
                override fun onError(e: Throwable?) {
                    Log.e("onError", "error ${e?.message}")
                }
            }).let {
            CompositeDisposable().dispose()
        }
    }
}