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
}