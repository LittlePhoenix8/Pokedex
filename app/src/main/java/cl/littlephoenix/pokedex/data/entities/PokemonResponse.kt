package cl.littlephoenix.pokedex.data.entities

data class PokemonResponse (var results: List<PokemonName>)

fun PokemonResponse.toEntity() = results.map { it.toEntity() }