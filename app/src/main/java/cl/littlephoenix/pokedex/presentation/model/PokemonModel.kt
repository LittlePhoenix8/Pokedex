package cl.littlephoenix.pokedex.presentation.model

import cl.littlephoenix.pokedex.data.entities.PokemonEntity

data class PokemonModel (var id: Int, var name: String, var urlPhoto: String)

fun PokemonEntity.toModel(): PokemonModel {
    return PokemonModel(id, name, photoUrl)
}