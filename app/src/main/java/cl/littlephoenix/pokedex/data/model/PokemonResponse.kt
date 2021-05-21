package cl.littlephoenix.pokedex.data.model

data class PokemonResponse (var results: Array<PokemonName>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as PokemonResponse
        if (!results.contentEquals(other.results)) return false
        return true
    }

    override fun hashCode(): Int {
        return results.contentHashCode()
    }
}