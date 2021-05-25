package cl.littlephoenix.pokedex.data.api

import javax.inject.Inject

class PokedexApiHelper @Inject constructor(private val pokedexApiService: PokedexApiService) {
    suspend fun getFirstGenPokemon() = pokedexApiService.getFirstGenPokemon()
}