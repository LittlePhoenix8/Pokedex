package cl.littlephoenix.pokedex.data.api

import cl.littlephoenix.pokedex.data.model.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexApiService {
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val BASE_IMG_URL = "https://pokeres.bastionbot.org/images/pokemon/"
    }

    @GET("type")
    suspend fun getTypes(): Response<TypeResponse>

    @GET("pokemon?limit=151")
    suspend fun getFirstGenPokemon(): Response<PokemonResponse>

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemonDetail(@Path("pokemonId") pokemonId: Int): Response<PokemonInfoResponse>

    @GET("pokemon/{pokemonId}/encounters")
    suspend fun getPokemonLocation(@Path("pokemonId") pokemonId: Int): Response<Array<PokemonLocationsResponse>>

    @GET("pokemon-species/{pokemonId}/")
    suspend fun getPokemonSpecie(@Path("pokemonId") pokemonId: Int): Response<PokemonSpecieResponse>

    @GET("evolution-chain/{pokemonId}/")
    suspend fun getPokemonEvolutions(@Path("pokemonId") pokemonId: Int): Response<PokemonEvolutionResponse>
}