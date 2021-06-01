package cl.littlephoenix.pokedex.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.littlephoenix.pokedex.data.entities.*

@Database(entities = [PokemonEntity::class, TypeEntity::class, AttackEntity::class,
    LocationEntity::class, SkillEntity::class, PokemonTypeCrossRef::class], version = 4, exportSchema = false)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao
    abstract fun getTypeDao(): TypeDao
    abstract fun getPokemonTypeCrossRefDao(): PokemonTypeCrossRefDao
    abstract fun getAttackDao(): AttackDao
    abstract fun getLocationDao(): LocationDao
    abstract fun getSkillDao(): SkillDao

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