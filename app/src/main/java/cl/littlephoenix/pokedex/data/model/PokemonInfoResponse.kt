package cl.littlephoenix.pokedex.data.model

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