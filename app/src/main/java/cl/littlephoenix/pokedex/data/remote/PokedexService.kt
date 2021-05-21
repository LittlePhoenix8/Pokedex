package cl.littlephoenix.pokedex.data.remote

import cl.littlephoenix.pokedex.data.model.PokemonEvolution
import cl.littlephoenix.pokedex.data.model.PokemonInfo
import cl.littlephoenix.pokedex.data.model.PokemonLocations
import cl.littlephoenix.pokedex.data.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexService {
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val BASE_IMG_URL = "https://pokeres.bastionbot.org/images/pokemon/"
        //https://pokeres.bastionbot.org/images/pokemon/151.png
    }

    @GET("pokemon?limit=151")
    suspend fun getFirstGenPokemon(): Response<PokemonResponse>?

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetail(@Path("pokemonName") pokemonName: String): Response<PokemonInfo>

    @GET("pokemon/{pokemonNumber}/encounters")
    suspend fun getPokemonLocation(@Path("pokemonNumber") pokemonNumber: Int): Response<Array<PokemonLocations>>

    @GET("evolution-chain/{pokemonNumber}/")
    suspend fun getPokemonEvolutions(@Path("pokemonNumber") pokemonNumber: Int): Response<PokemonEvolution>
}