package cl.littlephoenix.pokedex.data.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import cl.littlephoenix.pokedex.data.entities.AttackEntity
import cl.littlephoenix.pokedex.data.entities.PokemonEntity
import cl.littlephoenix.pokedex.data.entities.SkillEntity
import cl.littlephoenix.pokedex.presentation.model.PokemonModel
import cl.littlephoenix.pokedex.utils.getIdFromUrl
import cl.littlephoenix.pokedex.utils.getNameUppercase
import cl.littlephoenix.pokedex.utils.getPhotoUrl

data class PokemonInfoResponse(var id: Int,
                       var name: String,
                       var types: List<PokemonTypes>,
                       var species: Species,
                       var moves: List<Moves>,
                       var abilities: List<Abilities>,
                       var location_area_encounters: String)

data class PokemonTypes(var slot: Int, var type: Type)

data class Type(var name: String, var url: String)

data class Abilities(var ability: Ability)

data class Ability(var name: String)

data class Moves(var move: Move)

data class Move(var name: String)

data class Species(var name: String, var url: String)

fun PokemonInfoResponse.toModel(chainId: Int): PokemonModel {
    return PokemonModel(id = id,
        name = name.getNameUppercase(),
        urlPhoto = id.getPhotoUrl(),
        type = types.map { it.type.name.getNameUppercase() },
        attacks = moves.map { it.move.name.getNameUppercase() },
        skills = abilities.map { it.ability.name.getNameUppercase() },
        chainId = chainId,
        evolutions = listOf(),
        locations = listOf())
}

fun PokemonInfoResponse.toEntity(): PokemonEntity {
    return PokemonEntity(id = id,
        name = name.getNameUppercase(),
        photoUrl = id.getPhotoUrl(),
        chainId = -1)
}

fun Species.toModel(chainId: Int): PokemonModel {
    return PokemonModel(id = url.getIdFromUrl(),
        name = name.getNameUppercase(),
        url.getPhotoUrl(),
        type = listOf(),
        attacks = listOf(),
        skills = listOf(),
        chainId = chainId,
        evolutions = listOf(),
        locations = listOf())
}

fun Move.toEntity(id: Int): AttackEntity {
    return AttackEntity(id = 0, pokemonId = id, attack = name.replace("-", "").getNameUppercase())
}

fun Ability.toEntity(id: Int): SkillEntity {
    return SkillEntity(id = 0, pokemonId = id, skill = name.replace("-", "").getNameUppercase())
}