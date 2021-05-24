package cl.littlephoenix.pokedex.di

import android.content.Context
import cl.littlephoenix.pokedex.data.local.PokemonDao
import cl.littlephoenix.pokedex.data.local.PokemonDatabase
import cl.littlephoenix.pokedex.data.remote.PokedexRemoteDataSource
import cl.littlephoenix.pokedex.data.remote.PokedexService
import cl.littlephoenix.pokedex.data.repository.PokedexRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonModule {
    @Singleton
    @Provides
    fun getPokedexService(): PokedexService {
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(PokedexService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(PokedexService::class.java)
    }

    @Singleton
    @Provides
    fun providePokedexRemoteDataSource(pokedexService: PokedexService) =
        PokedexRemoteDataSource(pokedexService)

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext appContext: Context) =
        PokemonDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun getPokemonDao(database: PokemonDatabase) = database.getPokemonDao()

    @Singleton
    @Provides
    fun getPokemonRepository(dataSource: PokedexRemoteDataSource, dao: PokemonDao) =
        PokedexRepository(dataSource, dao)
}