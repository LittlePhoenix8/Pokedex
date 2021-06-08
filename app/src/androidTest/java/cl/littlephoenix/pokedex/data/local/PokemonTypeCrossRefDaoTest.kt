package cl.littlephoenix.pokedex.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.littlephoenix.pokedex.fakedata.TestUtil
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class PokemonTypeCrossRefDaoTest {
    private lateinit var pokemonTypeCrossRefDao: PokemonTypeCrossRefDao
    private lateinit var pokemonDatabase: PokemonDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        pokemonDatabase = Room.inMemoryDatabaseBuilder(
            context, PokemonDatabase::class.java).build()
        pokemonTypeCrossRefDao = pokemonDatabase.getPokemonTypeCrossRefDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        pokemonDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetByPokemonID() = runBlocking {
        val pokeTypes = TestUtil.getPokemonTypeCrossRef()
        pokemonTypeCrossRefDao.insertPokeType(pokeTypes)
        val id = 1
        val pokeTypeById = pokemonTypeCrossRefDao.getTypeByPokemonId(id)
        assertTrue(pokeTypes.size == pokeTypeById.size)
        assertTrue(pokeTypeById.first().pokemonId == id)
    }
}