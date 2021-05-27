package cl.littlephoenix.pokedex.di

import android.content.Context
import cl.littlephoenix.pokedex.data.local.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = PokemonDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun providePokemonDao(database: PokemonDatabase) = database.getPokemonDao()

    @Singleton
    @Provides
    fun provideTypeDao(database: PokemonDatabase) = database.getTypeDao()

    @Singleton
    @Provides
    fun provideAttackDao(database: PokemonDatabase) = database.getAttackDao()

    @Singleton
    @Provides
    fun provideLocationDao(database: PokemonDatabase) = database.getLocationDao()

    @Singleton
    @Provides
    fun provideSkillDao(database: PokemonDatabase) = database.getSkillDao()
}