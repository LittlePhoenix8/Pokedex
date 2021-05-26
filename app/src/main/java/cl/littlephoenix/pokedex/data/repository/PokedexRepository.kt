package cl.littlephoenix.pokedex.data.repository

import cl.littlephoenix.pokedex.data.api.PokedexApiService
import cl.littlephoenix.pokedex.data.model.PokemonResponse
import javax.inject.Inject

class PokedexRepository @Inject constructor(private val pokedexApiService: PokedexApiService) {

    suspend fun getFirstGenPokemon() = pokedexApiService.getFirstGenPokemon()
    suspend fun getPokemonDetail(pokemonId: Int) = pokedexApiService.getPokemonDetail(pokemonId)
    suspend fun getPokemonSpecie(pokemonId: Int) = pokedexApiService.getPokemonSpecie(pokemonId)
    suspend fun getPokemonEvolutions(pokemonId: Int) = pokedexApiService.getPokemonEvolutions(pokemonId)

    //TODO fix next
    //suspend fun getPokemonLocation(pokemonNumber: Int) = pokedexHelper.getPokemonLocation(pokemonNumber)
}