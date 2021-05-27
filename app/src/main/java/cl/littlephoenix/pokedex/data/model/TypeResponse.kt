package cl.littlephoenix.pokedex.data.model

import cl.littlephoenix.pokedex.data.entities.TypeEntity
import cl.littlephoenix.pokedex.utils.getIdFromUrl

data class TypeResponse(val results: List<Type>)

fun Type.toEntity() = TypeEntity(id = url.getIdFromUrl(), type = name)

fun TypeResponse.toEntity(): List<TypeEntity> {
    return results.map { it.toEntity() }
}