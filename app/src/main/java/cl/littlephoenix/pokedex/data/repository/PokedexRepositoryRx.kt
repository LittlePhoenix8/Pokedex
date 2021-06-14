package cl.littlephoenix.pokedex.data.repository

import cl.littlephoenix.pokedex.data.api.PokedexApiServiceRx
import javax.inject.Inject

class PokedexRepositoryRx @Inject constructor(
    private val pokedexApiService: PokedexApiServiceRx): PokedexRemoteRepositoryRx {
    override fun getSecondGenPokemon() = pokedexApiService.getSecondGenPokemon()
}