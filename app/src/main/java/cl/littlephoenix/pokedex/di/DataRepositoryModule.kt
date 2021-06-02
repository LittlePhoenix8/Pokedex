package cl.littlephoenix.pokedex.di

import cl.littlephoenix.pokedex.data.api.PokedexApiService
import cl.littlephoenix.pokedex.data.local.*
import cl.littlephoenix.pokedex.data.repository.PokedexLocalRepository
import cl.littlephoenix.pokedex.data.repository.PokedexRepository
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
    fun provideLocalRepository(pokemonDao: PokemonDao,
                               pokemonEvolutionDao: PokemonEvolutionDao,
                               typeDao: TypeDao,
                               pokemonTypeCrossRefDao: PokemonTypeCrossRefDao,
                               attackDao: AttackDao,
                               locationDao: LocationDao,
                               skillDao: SkillDao) =
        PokedexLocalRepository(pokemonDao, pokemonEvolutionDao, typeDao, pokemonTypeCrossRefDao, attackDao, locationDao, skillDao)
}