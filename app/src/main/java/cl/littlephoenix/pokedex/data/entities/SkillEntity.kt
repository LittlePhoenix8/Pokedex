package cl.littlephoenix.pokedex.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "skill", indices = [Index(value = ["skill_id", "pokemon_id"], unique = true)])
data class SkillEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "skill_id") val id: Int,
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "skill") val skill: String
)