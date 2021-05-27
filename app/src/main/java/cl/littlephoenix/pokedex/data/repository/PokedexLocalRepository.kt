package cl.littlephoenix.pokedex.data.repository

import cl.littlephoenix.pokedex.data.entities.*
import cl.littlephoenix.pokedex.data.local.*
import javax.inject.Inject

class PokedexLocalRepository @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val typeDao: TypeDao,
    private val attackDao: AttackDao,
    private val locationDao: LocationDao,
    private val skillDao: SkillDao) {
    suspend fun getAllPokemon() = pokemonDao.getAllPokemon()
    suspend fun saveAllPokemon(pokemonList: List<PokemonEntity>) = pokemonDao.insert(pokemonList)
    suspend fun saveAllTypes(typeList: List<TypeEntity>) = typeDao.insert(typeList)
    suspend fun saveAttacks(list: List<AttackEntity>) = attackDao.insert(list)
    suspend fun saveLocations(list: List<LocationEntity>) = locationDao.insert(list)
    suspend fun saveSkills(list: List<SkillEntity>) = skillDao.insert(list)
}