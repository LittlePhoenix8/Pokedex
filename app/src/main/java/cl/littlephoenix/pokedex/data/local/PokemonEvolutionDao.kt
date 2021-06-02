package cl.littlephoenix.pokedex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.littlephoenix.pokedex.data.entities.PokemonEvolutionEntity
@Dao
interface PokemonEvolutionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokeEvols(pokemon: List<PokemonEvolutionEntity>)

    @Query("SELECT * FROM pokeevols WHERE pokemon_id is (:id)")
    fun getPokemonEvolutionsById(id: Int): List<PokemonEvolutionEntity>
}