package cl.littlephoenix.pokedex.data.model

data class PokemonResponse (var result: Array<PokemonName>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as PokemonResponse
        if (!result.contentEquals(other.result)) return false
        return true
    }

    override fun hashCode(): Int {
        return result.contentHashCode()
    }
}