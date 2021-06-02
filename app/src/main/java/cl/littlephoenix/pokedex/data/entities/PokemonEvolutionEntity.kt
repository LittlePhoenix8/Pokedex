package cl.littlephoenix.pokedex.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "pokeevols", indices = [Index(value = ["evolution_id", "pokemon_id"], unique = true)])
data class PokemonEvolutionEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "evolution_id") val id: Int,
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "name_pokemon") val name: String,
    @ColumnInfo(name = "photo_pokemon") val photoUrl: String
    )