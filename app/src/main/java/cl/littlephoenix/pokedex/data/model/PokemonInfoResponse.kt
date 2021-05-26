package cl.littlephoenix.pokedex.data.model

import cl.littlephoenix.pokedex.presentation.model.PokemonModel
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

data class Type(var name: String)

data class Abilities(var ability: Ability)

data class Ability(var name: String)

data class Moves(var move: Move)

data class Move(var name: String)

data class Species(var name: String, var url: String)

fun PokemonInfoResponse.toModel(): PokemonModel {
    return PokemonModel(id = id,
        name = name.getNameUppercase(),
        urlPhoto = id.getPhotoUrl(),
        type = types.map { it.type.name.getNameUppercase() },
        attacks = moves.map { it.move.name.getNameUppercase().replace("-", " ") })
}