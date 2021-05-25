package cl.littlephoenix.pokedex.data.repository

import cl.littlephoenix.pokedex.data.api.PokedexApiService
import cl.littlephoenix.pokedex.data.model.PokemonResponse
import javax.inject.Inject

class PokedexRepository @Inject constructor(private val pokedesApiService: PokedexApiService) {

    suspend fun getFirstGenPokemon(): PokemonResponse = pokedesApiService.getFirstGenPokemon()

    /*suspend fun fetchTrendingMovies(): Flow<Result<TrendingMovieResponse>?> {
        return flow {
            emit(fetchTrendingMoviesCached())
            emit(Result.loading())
            val result = movieRemoteDataSource.fetchTrendingMovies()

            //Cache to database if response is successful
            if (result.status == Result.Status.SUCCESS) {
                result.data?.results?.let { it ->
                    movieDao.deleteAll(it)
                    movieDao.insertAll(it)
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }*/
    //TODO fix next
    /*suspend fun getPokemonDetail(pokemonName: String) = pokedexHelper.getPokemonDetail(pokemonName)
    suspend fun getPokemonLocation(pokemonNumber: Int) = pokedexHelper.getPokemonLocation(pokemonNumber)
    suspend fun getPokemonEvolutions(pokemonNumber: Int) = pokedexHelper.getPokemonEvolutions(pokemonNumber)*/
}