package cl.littlephoenix.pokedex.data.api

import cl.littlephoenix.pokedex.data.model.PokemonEvolutionResponse
import cl.littlephoenix.pokedex.data.model.PokemonInfoResponse
import cl.littlephoenix.pokedex.data.model.PokemonResponse
import cl.littlephoenix.pokedex.data.model.PokemonSpecieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexApiService {
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val BASE_IMG_URL = "https://pokeres.bastionbot.org/images/pokemon/"
    }

    @GET("pokemon?limit=151")
    suspend fun getFirstGenPokemon(): PokemonResponse?

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemonDetail(@Path("pokemonId") pokemonId: Int): PokemonInfoResponse?

    /*@GET("pokemon/{pokemonNumber}/encounters")
    suspend fun getPokemonLocation(@Path("pokemonNumber") pokemonNumber: Int): Response<Array<PokemonLocations>>*/

    @GET("pokemon-species/{pokemonId}/")
    suspend fun getPokemonSpecie(@Path("pokemonId") pokemonId: Int): PokemonSpecieResponse?

    @GET("evolution-chain/{pokemonId}/")
    suspend fun getPokemonEvolutions(@Path("pokemonId") pokemonId: Int): PokemonEvolutionResponse?
}