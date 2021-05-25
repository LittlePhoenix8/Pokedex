package cl.littlephoenix.pokedex.data.api

import cl.littlephoenix.pokedex.data.model.PokemonResponse
import retrofit2.http.GET

interface PokedexApiService {
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val BASE_IMG_URL = "https://pokeres.bastionbot.org/images/pokemon/"
    }

    @GET("pokemon?limit=151")
    suspend fun getFirstGenPokemon(): PokemonResponse?

    /*@GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetail(@Path("pokemonName") pokemonName: String): Response<PokemonInfo>

    @GET("pokemon/{pokemonNumber}/encounters")
    suspend fun getPokemonLocation(@Path("pokemonNumber") pokemonNumber: Int): Response<Array<PokemonLocations>>

    @GET("evolution-chain/{pokemonNumber}/")
    suspend fun getPokemonEvolutions(@Path("pokemonNumber") pokemonNumber: Int): Response<PokemonEvolution>*/
}