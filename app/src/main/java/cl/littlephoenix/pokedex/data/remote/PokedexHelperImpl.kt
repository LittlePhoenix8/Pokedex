package cl.littlephoenix.pokedex.data.remote

import cl.littlephoenix.pokedex.data.model.PokemonEvolution
import cl.littlephoenix.pokedex.data.model.PokemonInfo
import cl.littlephoenix.pokedex.data.model.PokemonLocations
import cl.littlephoenix.pokedex.data.model.PokemonResponse
import retrofit2.Response
import javax.inject.Inject

class PokedexHelperImpl @Inject constructor(
    private val pokedexService: PokedexService
    ): PokedexHelper {
    override suspend fun getFirstGenPokemon(): Response<PokemonResponse>? {
        return pokedexService.getFirstGenPokemon()
    }

    override suspend fun getPokemonDetail(pokemonName: String): Response<PokemonInfo> {
        return pokedexService.getPokemonDetail(pokemonName)
    }

    override suspend fun getPokemonLocation(pokemonNumber: Int): Response<Array<PokemonLocations>> {
        return pokedexService.getPokemonLocation(pokemonNumber)
    }

    override suspend fun getPokemonEvolutions(pokemonNumber: Int): Response<PokemonEvolution> {
        return pokedexService.getPokemonEvolutions(pokemonNumber)
    }
}