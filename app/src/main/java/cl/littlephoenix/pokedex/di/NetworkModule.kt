package cl.littlephoenix.pokedex.di

import cl.littlephoenix.pokedex.data.api.PokedexApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitClient(): PokedexApiService {
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(PokedexApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(PokedexApiService::class.java)
    }

    /*@Singleton
    @Provides
    fun getDatabase(@ApplicationContext appContext: Context) =
        PokemonDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun getPokemonDao(database: PokemonDatabase) = database.getPokemonDao()*/
}