package cl.littlephoenix.pokedex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.littlephoenix.pokedex.data.entities.LocationEntity

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(locations: List<LocationEntity>)

    @Query("SELECT * FROM location WHERE pokemon_id is (:id)")
    fun getLocationsByPokemon(id: Int): List<LocationEntity>

}