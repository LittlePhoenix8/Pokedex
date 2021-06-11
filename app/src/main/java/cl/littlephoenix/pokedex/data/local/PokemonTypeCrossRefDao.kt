package cl.littlephoenix.pokedex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.littlephoenix.pokedex.data.entities.PokemonTypeCrossRefEntity

@Dao
interface PokemonTypeCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokeType(pokemonType: List<PokemonTypeCrossRefEntity>)

    @Query("SELECT * FROM poketype WHERE pokemon_id is (:id)")
    suspend fun getTypeByPokemonId(id: Int): List<PokemonTypeCrossRefEntity>
}