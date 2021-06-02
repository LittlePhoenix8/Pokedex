package cl.littlephoenix.pokedex.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "attack",
    indices = [Index(value = ["attack_id", "pokemon_id"], unique = true)])
data class AttackEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "attack_id") val id: Int,
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "attack") val attack: String
)