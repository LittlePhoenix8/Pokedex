package cl.littlephoenix.pokedex.data.model

import cl.littlephoenix.pokedex.data.entities.PokemonEntity
import cl.littlephoenix.pokedex.presentation.model.PokemonModel
import cl.littlephoenix.pokedex.utils.getIdFromUrl
import cl.littlephoenix.pokedex.utils.getNameUppercase
import cl.littlephoenix.pokedex.utils.getPhotoUrl

data class PokemonName(var name: String, var url: String)

fun PokemonName.toEntity(): PokemonEntity {
    return PokemonEntity(url.getIdFromUrl(), name.getNameUppercase(), url.getPhotoUrl())
}

fun PokemonName.toModel(): PokemonModel {
    return PokemonModel(id = url.getIdFromUrl(),
        name = name.getNameUppercase(),
        urlPhoto = url.getPhotoUrl(),
        type = listOf(),
        attacks = listOf(),
        skills = listOf(),
        chainId = -1,
        evolutions = listOf(),
        locations = listOf())
}