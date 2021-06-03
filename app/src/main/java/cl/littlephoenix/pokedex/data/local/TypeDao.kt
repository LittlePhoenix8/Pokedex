package cl.littlephoenix.pokedex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.littlephoenix.pokedex.data.entities.TypeEntity

@Dao
interface TypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: List<TypeEntity>)

    @Query("SELECT * FROM type ORDER BY id_type ASC")
    suspend fun getAllTypes(): List<TypeEntity>

    @Query("SELECT * FROM type WHERE type_name is (:name)")
    suspend fun getTypeByName(name: String): List<TypeEntity>

    @Query("SELECT * FROM type WHERE id_type in (:ids)")
    suspend fun getTypeById(ids: List<Int>): List<TypeEntity>
}