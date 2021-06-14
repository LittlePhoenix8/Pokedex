package cl.littlephoenix.pokedex.data.repository

import cl.littlephoenix.pokedex.data.model.*
import retrofit2.Response

interface PokedexRemoteRepository {
    suspend fun getFirstGenPokemon(): Response<PokemonResponse>
    suspend fun getTypes(): Response<TypeResponse>
    suspend fun getPokemonDetail(pokemonId: Int): Response<PokemonInfoResponse>
    suspend fun getPokemonSpecie(pokemonId: Int): Response<PokemonSpecieResponse>
    suspend fun getPokemonEvolutions(pokemonId: Int): Response<PokemonEvolutionResponse>
    suspend fun getPokemonLocation(pokemonId: Int): Response<Array<PokemonLocationsResponse>>
}