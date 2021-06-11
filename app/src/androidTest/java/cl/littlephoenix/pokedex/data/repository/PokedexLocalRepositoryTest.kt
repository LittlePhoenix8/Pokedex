package cl.littlephoenix.pokedex.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import cl.littlephoenix.pokedex.data.local.*
import cl.littlephoenix.pokedex.fakedata.TestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PokedexLocalRepositoryTest {
    private lateinit var pokedexLocalRepository: PokedexLocalRepository
    private lateinit var pokemonDatabase: PokemonDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        pokemonDatabase = Room.inMemoryDatabaseBuilder(
            context, PokemonDatabase::class.java).build()
        pokedexLocalRepository = PokedexLocalRepository(pokemonDatabase.getPokemonDao(),
            pokemonDatabase.getPokemonEvolutionDao(),
            pokemonDatabase.getTypeDao(),
            pokemonDatabase.getPokemonTypeCrossRefDao(),
            pokemonDatabase.getAttackDao(),
            pokemonDatabase.getLocationDao(),
            pokemonDatabase.getSkillDao())
    }

    @After
    fun tearDown() {
        pokemonDatabase.close()
    }

    @Test
    fun givenInitWhenSaveAllPokemonThenGetAllPokemon() = runBlocking {
        val pokemonList = TestUtil.getPokemon()
        pokedexLocalRepository.saveAllPokemon(pokemonList)
        val pokemonDb = pokedexLocalRepository.getAllPokemon()
        assertTrue(pokemonList.size == pokemonDb.size)
        assertTrue(pokemonList.first().name == pokemonDb.first().name)
    }

    @Test
    fun givenInitWhenSaveEmptyPokemonThenGetEmptyPokemon() = runBlocking {
        val pokemonList = TestUtil.getPokemonEmpty()
        pokedexLocalRepository.saveAllPokemon(pokemonList)
        val pokemonDb = pokedexLocalRepository.getAllPokemon()
        assertTrue(pokemonList.size == pokemonDb.size)
        assertTrue(pokemonList.isEmpty())
        assertTrue(pokemonDb.isEmpty())
    }

    @Test
    fun givenInitWhenSaveAllPokemonThenGetPokemonWithId() = runBlocking {
        val id = 1
        val pokemonList = TestUtil.getPokemon()
        pokedexLocalRepository.saveAllPokemon(pokemonList)
        val pokemonDb = pokedexLocalRepository.getPokemon(id)
        assertNotNull(pokemonDb)
        assertTrue(pokemonDb!!.id == id)
    }

    @Test
    fun givenInitWhenSaveAllPokemonThenUpdateAllPokemon() = runBlocking {
        val pokemonList = TestUtil.getPokemonWithoutChainId()
        pokedexLocalRepository.saveAllPokemon(pokemonList)
        val pokemonDb = pokedexLocalRepository.getAllPokemon()

        val pokemonUpdateList = TestUtil.getPokemon()
        pokedexLocalRepository.updateAllPokemon(pokemonUpdateList)
        val pokemonUpdateDb = pokedexLocalRepository.getAllPokemon()

        assertNotNull(pokemonDb)
        assertTrue(pokemonDb.first().chainId == -1)

        assertNotNull(pokemonUpdateDb)
        assertTrue(pokemonUpdateDb.first().chainId != -1)
    }

    //evolutions
    @Test
    fun givenInitWhenSaveEvolutionsThenGetEvolutionById() = runBlocking {
        val id = 1
        val evolutionsList = TestUtil.getEvolutions()
        pokedexLocalRepository.savePokemonEvolutions(evolutionsList)
        val evolutionsDb = pokedexLocalRepository.getPokemonEvolutions(id)
        assertNotNull(evolutionsDb)
        assertTrue(evolutionsDb.first().id == id)
    }

    @Test
    fun givenInitWhenSaveEvolutionsEmptyThenGetEvolutionByIdEmpty() = runBlocking {
        val id = 1
        val evolutionsList = TestUtil.getEvolutionsEmpty()
        pokedexLocalRepository.savePokemonEvolutions(evolutionsList)
        val evolutionsDb = pokedexLocalRepository.getPokemonEvolutions(id)
        assertTrue(evolutionsDb.isEmpty())
    }

    //attacks
    @Test
    fun givenInitWhenSaveAttacksThenGetAttacksByPokemonId() = runBlocking {
        val id = 1
        val attacksList = TestUtil.getAttacks()
        pokedexLocalRepository.saveAttacks(attacksList)
        val attacksDb = pokedexLocalRepository.getAttacksByPokemon(id)
        assertNotNull(attacksDb)
        assertTrue(attacksDb.first().id == id)
    }

    @Test
    fun givenInitWhenSaveAttacksEmptyThenGetAttacksByPokemonIdEmpty() = runBlocking {
        val id = 1
        val attacksList = TestUtil.getAttacksEmpty()
        pokedexLocalRepository.saveAttacks(attacksList)
        val attacksDb = pokedexLocalRepository.getAttacksByPokemon(id)
        assertTrue(attacksDb.isEmpty())
    }

    //attacks
    @Test
    fun givenInitWhenSaveSkillsThenGetSkillsByPokemonId() = runBlocking {
        val id = 1
        val skillsList = TestUtil.getSkill()
        pokedexLocalRepository.saveSkills(skillsList)
        val skillsDb = pokedexLocalRepository.getSkillsByPokemon(id)
        assertNotNull(skillsDb)
        assertTrue(skillsDb.first().id == id)
    }

    @Test
    fun givenInitWhenSaveSkillsEmptyThenGetSkillsByPokemonIdEmpty() = runBlocking {
        val id = 1
        val skillsList = TestUtil.getSkillEmpty()
        pokedexLocalRepository.saveSkills(skillsList)
        val skillsDb = pokedexLocalRepository.getSkillsByPokemon(id)
        assertTrue(skillsDb.isEmpty())
    }

    //locations
    @Test
    fun givenInitWhenSaveLocationsThenGetLocationsByPokemonId() = runBlocking {
        val id = 1
        val locationsList = TestUtil.getLocations()
        pokedexLocalRepository.saveLocations(locationsList)
        val locationsDb = pokedexLocalRepository.getLocationsByPokemon(id)
        assertNotNull(locationsDb)
        assertTrue(locationsDb.first().id == id)
    }

    @Test
    fun givenInitWhenSaveLocationsEmptyThenGetLocationsByPokemonIdEmpty() = runBlocking {
        val id = 1
        val locationsList = TestUtil.getLocationsEmpty()
        pokedexLocalRepository.saveLocations(locationsList)
        val locationsDb = pokedexLocalRepository.getLocationsByPokemon(id)
        assertTrue(locationsDb.isEmpty())
    }

    //types
    @Test
    fun givenInitWhenSaveTypesThenGetTypesByPokemonId() = runBlocking {
        val ids = listOf(12, 4)
        val typesList = TestUtil.getTypes()
        pokedexLocalRepository.saveTypes(typesList)
        val typesDb = pokedexLocalRepository.getTypesById(ids)
        assertNotNull(typesDb)
        assertTrue(typesDb.size == ids.size)
    }

    @Test
    fun givenInitWhenSaveTypesThenGetTypesByPokemonIdEmpty() = runBlocking {
        val ids = listOf(1)
        val typesList = TestUtil.getTypes()
        pokedexLocalRepository.saveTypes(typesList)
        val typesDb = pokedexLocalRepository.getTypesById(ids)
        assertTrue(typesDb.isEmpty())
    }

    @Test
    fun givenInitWhenSaveTypesEmptyThenGetTypesByPokemonIdEmpty() = runBlocking {
        val ids = listOf(1)
        val typesList = TestUtil.getTypesEmpty()
        pokedexLocalRepository.saveTypes(typesList)
        val typesDb = pokedexLocalRepository.getTypesById(ids)
        assertTrue(typesDb.isEmpty())
    }

    //types cross ref
    @Test
    fun givenInitWhenSavePokeTypesThenGetPokeTypesByPokemonId() = runBlocking {
        val id = 1
        val typesList = TestUtil.getPokemonTypeCrossRef()
        pokedexLocalRepository.savePokeTypes(typesList)
        val typesDb = pokedexLocalRepository.getPokeTypesByPokemon(id)
        assertNotNull(typesDb)
        assertTrue(typesDb.first().pokemonId == id)
    }

    @Test
    fun givenInitWhenSavePokeTypesThenGetPokeTypesByPokemonIdEmpty() = runBlocking {
        val id = 2
        val typesList = TestUtil.getPokemonTypeCrossRef()
        pokedexLocalRepository.savePokeTypes(typesList)
        val typesDb = pokedexLocalRepository.getPokeTypesByPokemon(id)
        assertTrue(typesDb.isEmpty())
    }

    @Test
    fun givenInitWhenSavePokeTypesEmptyThenGetPokeTypesByPokemonIdEmpty() = runBlocking {
        val id = 1
        val typesList = TestUtil.getPokemonTypeCrossRefEmpty()
        pokedexLocalRepository.savePokeTypes(typesList)
        val typesDb = pokedexLocalRepository.getPokeTypesByPokemon(id)
        assertTrue(typesDb.isEmpty())
    }
}