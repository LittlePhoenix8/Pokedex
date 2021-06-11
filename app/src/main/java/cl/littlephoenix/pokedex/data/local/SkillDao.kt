package cl.littlephoenix.pokedex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.littlephoenix.pokedex.data.entities.SkillEntity

@Dao
interface SkillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(skills: List<SkillEntity>)

    @Query("SELECT * FROM skill WHERE pokemon_id is (:id)")
    suspend fun getSkillsByPokemon(id: Int): List<SkillEntity>
}