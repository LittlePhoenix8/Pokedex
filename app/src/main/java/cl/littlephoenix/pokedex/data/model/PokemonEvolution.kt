package cl.littlephoenix.pokedex.data.model

data class PokemonEvolution(var chain: ChainEvol)

data class ChainEvol(var evolves_to: Array<EvolutionInfo>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChainEvol

        if (!evolves_to.contentEquals(other.evolves_to)) return false

        return true
    }

    override fun hashCode(): Int {
        return evolves_to.contentHashCode()
    }
}

data class EvolutionInfo(var is_baby: Boolean, var evolves_to: Array<EvolTo>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EvolutionInfo

        if (is_baby != other.is_baby) return false
        if (!evolves_to.contentEquals(other.evolves_to)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = is_baby.hashCode()
        result = 31 * result + evolves_to.contentHashCode()
        return result
    }
}

data class EvolTo(var species: Species, var evolves_to: Array<EvolutionInfo>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EvolTo

        if (species != other.species) return false
        if (!evolves_to.contentEquals(other.evolves_to)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = species.hashCode()
        result = 31 * result + evolves_to.contentHashCode()
        return result
    }
}