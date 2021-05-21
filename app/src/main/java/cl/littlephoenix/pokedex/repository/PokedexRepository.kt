package cl.littlephoenix.pokedex.repository

import cl.littlephoenix.pokedex.data.remote.PokedexHelper
import javax.inject.Inject

class PokedexRepository @Inject constructor(private val pokedexHelper: PokedexHelper) {
    suspend fun getFirstGenPokemon() = pokedexHelper.getFirstGenPokemon()
    suspend fun getPokemonDetail(pokemonName: String) = pokedexHelper.getPokemonDetail(pokemonName)
    suspend fun getPokemonLocation(pokemonNumber: Int) = pokedexHelper.getPokemonLocation(pokemonNumber)
    suspend fun getPokemonEvolutions(pokemonNumber: Int) = pokedexHelper.getPokemonEvolutions(pokemonNumber)
}