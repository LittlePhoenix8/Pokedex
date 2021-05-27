package cl.littlephoenix.pokedex.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "type", indices = [Index(value = ["id_type"], unique = true)])
data class TypeEntity(
    @PrimaryKey @ColumnInfo(name = "id_type") val id: Int,
    @ColumnInfo(name = "type_name") val type: String
)

@Entity(primaryKeys = ["pokemonId", "typeId"])
data class PokemonTypeCrossRef(
    val pokemonId: Int,
    val typeId: Int
)