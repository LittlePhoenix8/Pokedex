package cl.littlephoenix.pokedex.di

import cl.littlephoenix.pokedex.data.remote.PokedexHelper
import cl.littlephoenix.pokedex.data.remote.PokedexHelperImpl
import cl.littlephoenix.pokedex.data.remote.PokedexService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun getPokedexService(): PokedexService {
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(PokedexService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(PokedexService::class.java)
    }

    @Provides
    @Singleton
    fun providePokedexHelper(pokedexHelper: PokedexHelperImpl): PokedexHelper = pokedexHelper
}