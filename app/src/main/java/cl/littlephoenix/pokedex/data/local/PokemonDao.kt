package cl.littlephoenix.pokedex.data.local

import androidx.room.*
import cl.littlephoenix.pokedex.data.entities.PokemonEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: List<PokemonEntity>)

    @Update
    suspend fun update(pokemon: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon ORDER BY id_pokemon ASC")
    fun getAllPokemon(): List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE name_pokemon is (:name)")
    fun getAllPokemonByName(name: String): List<PokemonEntity>
}