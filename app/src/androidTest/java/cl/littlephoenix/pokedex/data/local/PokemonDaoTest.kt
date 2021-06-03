package cl.littlephoenix.pokedex.data.local

import android.content.Context
import androidx.room.Room
import org.junit.Before
import org.junit.runner.RunWith
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.littlephoenix.pokedex.fakedata.TestUtil
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class PokemonDaoTest {
    private lateinit var pokemonDao: PokemonDao
    private lateinit var pokemonDatabase: PokemonDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        pokemonDatabase = Room.inMemoryDatabaseBuilder(
            context, PokemonDatabase::class.java).build()
        pokemonDao = pokemonDatabase.getPokemonDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        pokemonDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetList() = runBlocking {
        val pokemonList = TestUtil.getPokemon()
        pokemonDao.insert(pokemonList)
        val pokemonDb = pokemonDao.getAllPokemon()
        assertTrue(pokemonList.size == pokemonDb.size)
        assertTrue(pokemonList.first().name == pokemonDb.first().name)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetById() = runBlocking {
        val pokemonList = TestUtil.getPokemon()
        pokemonDao.insert(pokemonList)
        val id = 1
        val pokemonDb = pokemonDao.getPokemonById(id)
        assertTrue(pokemonDb != null)
        assertTrue(pokemonDb!!.id == id)
    }
}