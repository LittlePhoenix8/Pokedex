package cl.littlephoenix.pokedex.data.repository

import cl.littlephoenix.pokedex.data.entities.*
import cl.littlephoenix.pokedex.data.local.*
import javax.inject.Inject

class PokedexRoomRepository @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val pokemonEvolutionDao: PokemonEvolutionDao,
    private val typeDao: TypeDao,
    private val pokemonTypeCrossRefDao: PokemonTypeCrossRefDao,
    private val attackDao: AttackDao,
    private val locationDao: LocationDao,
    private val skillDao: SkillDao): PokedexLocalRepository {
    //pokemon
    override suspend fun getAllPokemon() = pokemonDao.getAllPokemon()
    override suspend fun getPokemon(id: Int) = pokemonDao.getPokemonById(id)
    override suspend fun saveAllPokemon(pokemon: List<PokemonEntity>) = pokemonDao.insert(pokemon)
    override suspend fun updateAllPokemon(pokemon: List<PokemonEntity>) = pokemonDao.update(pokemon)

    override fun saveSecondGen(pokemon: List<PokemonEntity>) = pokemonDao.insertSecondGen(pokemon)

    //pokemon evolutions
    override suspend fun getPokemonEvolutions(id: Int) =
        pokemonEvolutionDao.getPokemonEvolutionsById(id)
    override suspend fun savePokemonEvolutions(evolutions: List<PokemonEvolutionEntity>) =
        pokemonEvolutionDao.insertPokeEvols(evolutions)

    //type
    override suspend fun saveTypes(typeList: List<TypeEntity>) = typeDao.insert(typeList)
    override suspend fun getTypesById(ids: List<Int>) = typeDao.getTypeById(ids)
    override suspend fun savePokeTypes(typeList: List<PokemonTypeCrossRefEntity>) =
        pokemonTypeCrossRefDao.insertPokeType(typeList)
    override suspend fun getPokeTypesByPokemon(id: Int) =
        pokemonTypeCrossRefDao.getTypeByPokemonId(id)

    //attack
    override suspend fun saveAttacks(list: List<AttackEntity>) = attackDao.insert(list)
    override suspend fun getAttacksByPokemon(id: Int) = attackDao.getAttacksByPokemon(id)

    //skill
    override suspend fun saveSkills(list: List<SkillEntity>) = skillDao.insert(list)
    override suspend fun getSkillsByPokemon(id: Int) = skillDao.getSkillsByPokemon(id)

    //locations
    override suspend fun saveLocations(list: List<LocationEntity>) = locationDao.insert(list)
    override suspend fun getLocationsByPokemon(id: Int) = locationDao.getLocationsByPokemon(id)
}