package cl.littlephoenix.pokedex.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon", indices = [Index(value = ["id_pokemon"], unique = true)])
data class PokemonEntity(
    @PrimaryKey @ColumnInfo(name = "id_pokemon") val id: Int,
    @ColumnInfo(name = "name_pokemon") val name: String,
    @ColumnInfo(name = "photo_pokemon") val photoUrl: String,
    @ColumnInfo(name = "chain_id") val chainId: Int)