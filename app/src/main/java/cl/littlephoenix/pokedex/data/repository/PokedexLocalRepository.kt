package cl.littlephoenix.pokedex.data.repository

import cl.littlephoenix.pokedex.data.entities.PokemonEntity
import cl.littlephoenix.pokedex.data.local.PokemonDao
import javax.inject.Inject

class PokedexLocalRepository @Inject constructor(private val pokemonDao: PokemonDao) {
    suspend fun getAllPokemon() = pokemonDao.getAllPokemon()

    suspend fun saveAllPokemon(pokemonList: List<PokemonEntity>) = pokemonDao.insert(pokemonList)
}