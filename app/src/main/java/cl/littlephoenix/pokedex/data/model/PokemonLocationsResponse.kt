package cl.littlephoenix.pokedex.data.model

import cl.littlephoenix.pokedex.data.entities.LocationEntity
import cl.littlephoenix.pokedex.utils.getNameUppercase

data class PokemonLocationsResponse(var location_area: LocationArea)

data class LocationArea(var name: String)

fun LocationArea.toEntity(id: Int): LocationEntity {
    return LocationEntity(id = 0, pokemonId = id, location = name.replace("-", " ").getNameUppercase())
}