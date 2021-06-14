package cl.littlephoenix.pokedex.data.local

import androidx.room.*
import cl.littlephoenix.pokedex.data.entities.PokemonEntity
import io.reactivex.rxjava3.core.Completable

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemon: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSecondGen(pokemon: List<PokemonEntity>): Completable

    @Update
    suspend fun update(pokemon: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon ORDER BY id_pokemon ASC")
    suspend fun getAllPokemon(): List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE id_pokemon is (:id)")
    suspend fun getPokemonById(id: Int): PokemonEntity?
}