package cl.littlephoenix.pokedex.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.littlephoenix.pokedex.data.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var instance: PokemonDatabase? = null

        fun getDatabase(context: Context): PokemonDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(appContext: Context) = Room.databaseBuilder(
            appContext.applicationContext, PokemonDatabase::class.java, "pokemon_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}