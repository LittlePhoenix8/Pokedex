package cl.littlephoenix.pokedex.data.api

import javax.inject.Inject

class PokedexApiHelper @Inject constructor(private val pokedexApiService: PokedexApiService) {
    suspend fun getTypes() = pokedexApiService.getTypes()
    suspend fun getFirstGenPokemon() = pokedexApiService.getFirstGenPokemon()
    suspend fun getPokemonDetail(id: Int) = pokedexApiService.getPokemonDetail(id)
    suspend fun getPokemonSpecie(id: Int) = pokedexApiService.getPokemonSpecie(id)
    suspend fun getPokemonEvolutions(id: Int) = pokedexApiService.getPokemonEvolutions(id)
    suspend fun getPokemonLocation(id: Int) = pokedexApiService.getPokemonLocation(id)
}