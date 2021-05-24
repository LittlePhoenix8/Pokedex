package cl.littlephoenix.pokedex.data.repository

import cl.littlephoenix.pokedex.data.entities.toEntity
import cl.littlephoenix.pokedex.data.local.PokemonDao
import cl.littlephoenix.pokedex.data.remote.PokedexRemoteDataSource
import cl.littlephoenix.pokedex.helper.performGetOperation
import javax.inject.Inject

class PokedexRepository @Inject constructor(
    private val pokedexRemoteDataSource: PokedexRemoteDataSource,
    private val pokemonDao: PokemonDao) {
    fun getFirstGenPokemon() = performGetOperation(
        databaseQuery = { pokemonDao.getAllPokemon() },
        networkCall = { pokedexRemoteDataSource.getFirstGenPokemon() },
        saveCallResult = { pokemonDao.insert(it.toEntity()) }
    )
    //TODO fix next
    /*suspend fun getPokemonDetail(pokemonName: String) = pokedexHelper.getPokemonDetail(pokemonName)
    suspend fun getPokemonLocation(pokemonNumber: Int) = pokedexHelper.getPokemonLocation(pokemonNumber)
    suspend fun getPokemonEvolutions(pokemonNumber: Int) = pokedexHelper.getPokemonEvolutions(pokemonNumber)*/
}