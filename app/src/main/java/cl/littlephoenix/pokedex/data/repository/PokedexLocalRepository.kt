package cl.littlephoenix.pokedex.data.repository

import cl.littlephoenix.pokedex.data.entities.*
import io.reactivex.rxjava3.core.Completable

interface PokedexLocalRepository {
    suspend fun getAllPokemon(): List<PokemonEntity>
    suspend fun getPokemon(id: Int): PokemonEntity?
    suspend fun saveAllPokemon(pokemon: List<PokemonEntity>)
    suspend fun updateAllPokemon(pokemon: List<PokemonEntity>)

    fun saveSecondGen(pokemon: List<PokemonEntity>): Completable

    //pokemon evolutions
    suspend fun getPokemonEvolutions(id: Int): List<PokemonEvolutionEntity>
    suspend fun savePokemonEvolutions(evolutions: List<PokemonEvolutionEntity>)

    //type
    suspend fun saveTypes(typeList: List<TypeEntity>)
    suspend fun getTypesById(ids: List<Int>): List<TypeEntity>
    suspend fun savePokeTypes(typeList: List<PokemonTypeCrossRefEntity>)
    suspend fun getPokeTypesByPokemon(id: Int): List<PokemonTypeCrossRefEntity>

    //attack
    suspend fun saveAttacks(list: List<AttackEntity>)
    suspend fun getAttacksByPokemon(id: Int): List<AttackEntity>

    //skill
    suspend fun saveSkills(list: List<SkillEntity>)
    suspend fun getSkillsByPokemon(id: Int): List<SkillEntity>

    //locations
    suspend fun saveLocations(list: List<LocationEntity>)
    suspend fun getLocationsByPokemon(id: Int): List<LocationEntity>
}