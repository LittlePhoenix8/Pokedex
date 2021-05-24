package cl.littlephoenix.pokedex.data.entities

data class PokemonName(var name: String, var url: String)

fun PokemonName.toEntity(): PokemonEntity {
    val pokeNumber = url.split("/")
    val id = pokeNumber[pokeNumber.size - 2].toInt()
    return PokemonEntity(id, name)
}