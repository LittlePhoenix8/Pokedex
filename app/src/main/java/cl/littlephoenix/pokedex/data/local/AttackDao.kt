package cl.littlephoenix.pokedex.data.local

import androidx.room.*
import cl.littlephoenix.pokedex.data.entities.AttackEntity

@Dao
interface AttackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(attacks: List<AttackEntity>)

    @Query("SELECT * FROM attack WHERE pokemon_id is (:id)")
    suspend fun getAttacksByPokemon(id: Int): List<AttackEntity>
}