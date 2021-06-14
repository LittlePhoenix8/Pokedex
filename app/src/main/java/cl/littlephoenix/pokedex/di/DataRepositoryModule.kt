package cl.littlephoenix.pokedex.di

import cl.littlephoenix.pokedex.data.api.PokedexApiService
import cl.littlephoenix.pokedex.data.api.PokedexApiServiceRx
import cl.littlephoenix.pokedex.data.local.*
import cl.littlephoenix.pokedex.data.repository.PokedexRepository
import cl.littlephoenix.pokedex.data.repository.PokedexRepositoryRx
import cl.littlephoenix.pokedex.data.repository.PokedexRoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataRepositoryModule {
    @Provides
    fun provideDataRepository(apiService: PokedexApiService) = PokedexRepository(apiService)

    @Provides
    fun provideDataRepositoryRx(apiService: PokedexApiServiceRx) = PokedexRepositoryRx(apiService)

    @Provides
    fun provideLocalRepository(pokemonDao: PokemonDao,
                               pokemonEvolutionDao: PokemonEvolutionDao,
                               typeDao: TypeDao,
                               pokemonTypeCrossRefDao: PokemonTypeCrossRefDao,
                               attackDao: AttackDao,
                               locationDao: LocationDao,
                               skillDao: SkillDao) =
        PokedexRoomRepository(pokemonDao, pokemonEvolutionDao, typeDao, pokemonTypeCrossRefDao, attackDao, locationDao, skillDao)
}