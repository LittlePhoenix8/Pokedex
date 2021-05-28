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
    //pokemon
    suspend fun getAllPokemon() = pokemonDao.getAllPokemon()
    suspend fun getPokemon(id: Int) = pokemonDao.getPokemonById(id)
    suspend fun saveAllPokemon(pokemonList: List<PokemonEntity>) = pokemonDao.insert(pokemonList)
    suspend fun updateAllPokemon(pokemonList: List<PokemonEntity>) = pokemonDao.update(pokemonList)

    //type
    suspend fun saveAllTypes(typeList: List<TypeEntity>) = typeDao.insert(typeList)

    //attack
    suspend fun saveAttacks(list: List<AttackEntity>) = attackDao.insert(list)
    suspend fun getAttacksByPokemon(id: Int) = attackDao.getAttacksByPokemon(id)

    //skill
    suspend fun saveSkills(list: List<SkillEntity>) = skillDao.insert(list)
    suspend fun getSkillsByPokemon(id: Int) = skillDao.getSkillsByPokemon(id)

    //locations
    suspend fun saveLocations(list: List<LocationEntity>) = locationDao.insert(list)
    suspend fun getLocationsByPokemon(id: Int) = locationDao.getLocationsByPokemon(id)
}