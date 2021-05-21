package cl.littlephoenix.pokedex.data.remote

import cl.littlephoenix.pokedex.data.model.PokemonEvolution
import cl.littlephoenix.pokedex.data.model.PokemonInfo
import cl.littlephoenix.pokedex.data.model.PokemonLocations
import cl.littlephoenix.pokedex.data.model.PokemonResponse
import retrofit2.Response

interface PokedexHelper {
    suspend fun getFirstGenPokemon(): Response<PokemonResponse>?
    suspend fun getPokemonDetail(pokemonName: String): Response<PokemonInfo>
    suspend fun getPokemonLocation(pokemonNumber: Int): Response<Array<PokemonLocations>>
    suspend fun getPokemonEvolutions(pokemonNumber: Int): Response<PokemonEvolution>
}