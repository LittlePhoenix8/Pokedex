package cl.littlephoenix.pokedex.data.remote

import javax.inject.Inject

class PokedexRemoteDataSource @Inject constructor(
    private val pokedexService: PokedexService
    ): BaseDataSource() {
    suspend fun getFirstGenPokemon() = getResult {
        pokedexService.getFirstGenPokemon()
    }

    suspend fun getPokemonDetail(pokemonName: String) = getResult {
        pokedexService.getPokemonDetail(pokemonName)
    }

    suspend fun getPokemonLocation(pokemonNumber: Int) = getResult {
        pokedexService.getPokemonLocation(pokemonNumber)
    }

    suspend fun getPokemonEvolutions(pokemonNumber: Int) = getResult {
        pokedexService.getPokemonEvolutions(pokemonNumber)
    }
}