package cl.littlephoenix.pokedex.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "location", indices = [Index(value = ["location_id", "pokemon_id"], unique = true)])
data class LocationEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "location_id") val id: Int,
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "location") val location: String
)