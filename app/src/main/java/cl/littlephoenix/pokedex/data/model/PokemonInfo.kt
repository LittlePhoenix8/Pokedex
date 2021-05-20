package cl.littlephoenix.pokedex.data.model

data class PokemonInfo(var id: Int,
                       var name: String,
                       var types: Array<PokemonTypes>,
                       var species: Species,
                       var moves: Array<Moves>,
                       var abilities: Array<Abilities>,
                       var location_area_encounters: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as PokemonInfo
        if (id != other.id) return false
        if (name != other.name) return false
        if (!types.contentEquals(other.types)) return false
        if (species != other.species) return false
        if (!moves.contentEquals(other.moves)) return false
        if (!abilities.contentEquals(other.abilities)) return false
        if (location_area_encounters != other.location_area_encounters) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + types.contentHashCode()
        result = 31 * result + species.hashCode()
        result = 31 * result + moves.contentHashCode()
        result = 31 * result + abilities.contentHashCode()
        result = 31 * result + location_area_encounters.hashCode()
        return result
    }
}

data class PokemonTypes(var slot: Int, var type: Type)

data class Type(var name: String)

data class Abilities(var ability: Ability)

data class Ability(var name: String)

data class Moves(var move: Move)

data class Move(var name: String)

data class Species(var name: String, var url: String)