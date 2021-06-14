package cl.littlephoenix.pokedex.data.api

import cl.littlephoenix.pokedex.data.model.PokemonResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface PokedexApiServiceRx {
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val BASE_IMG_URL = "https://pokeres.bastionbot.org/images/pokemon/"
    }

    @GET("pokemon?limit=100&offset= 151")
    fun getSecondGenPokemon(): Flowable<PokemonResponse>
}