package cl.littlephoenix.pokedex.data.repository

import cl.littlephoenix.pokedex.data.api.PokedexApiService
import cl.littlephoenix.pokedex.data.model.PokemonResponse
import javax.inject.Inject

class PokedexRepository @Inject constructor(
    private val pokedexApiService: PokedexApiService): PokedexRemoteRepository {
    override suspend fun getFirstGenPokemon() = pokedexApiService.getFirstGenPokemon()
    override suspend fun getTypes() = pokedexApiService.getTypes()
    override suspend fun getPokemonDetail(pokemonId: Int) = pokedexApiService.getPokemonDetail(pokemonId)
    override suspend fun getPokemonSpecie(pokemonId: Int) = pokedexApiService.getPokemonSpecie(pokemonId)
    override suspend fun getPokemonEvolutions(pokemonId: Int) = pokedexApiService.getPokemonEvolutions(pokemonId)
    override suspend fun getPokemonLocation(pokemonId: Int) = pokedexApiService.getPokemonLocation(pokemonId)
}