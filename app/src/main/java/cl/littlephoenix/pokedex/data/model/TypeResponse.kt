package cl.littlephoenix.pokedex.data.model

import cl.littlephoenix.pokedex.data.entities.PokemonTypeCrossRef
import cl.littlephoenix.pokedex.data.entities.TypeEntity
import cl.littlephoenix.pokedex.utils.getIdFromUrl
import cl.littlephoenix.pokedex.utils.getNameUppercase

data class TypeResponse(val results: List<Type>)

fun Type.toEntity() = TypeEntity(id = url.getIdFromUrl(), type = name.getNameUppercase())

fun Type.toPokeTypeEntity(id: Int) = PokemonTypeCrossRef(pokemonId = id, typeId = url.getIdFromUrl())

fun TypeResponse.toEntity(): List<TypeEntity> {
    return results.map { it.toEntity() }
}