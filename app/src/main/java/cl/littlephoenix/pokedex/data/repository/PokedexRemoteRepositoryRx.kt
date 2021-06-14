package cl.littlephoenix.pokedex.data.repository

import cl.littlephoenix.pokedex.data.model.PokemonResponse
import io.reactivex.rxjava3.core.Flowable

interface PokedexRemoteRepositoryRx {
    fun getSecondGenPokemon(): Flowable<PokemonResponse>
}