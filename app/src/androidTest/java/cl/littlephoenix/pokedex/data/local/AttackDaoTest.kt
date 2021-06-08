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
class AttackDaoTest {
    private lateinit var attackDao: AttackDao
    private lateinit var pokemonDatabase: PokemonDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        pokemonDatabase = Room.inMemoryDatabaseBuilder(
            context, PokemonDatabase::class.java).build()
        attackDao = pokemonDatabase.getAttackDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        pokemonDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetByPokemonID() = runBlocking {
        val attacks = TestUtil.getAttacks()
        attackDao.insert(attacks)
        val id = 1
        val attacksById = attackDao.getAttacksByPokemon(id)
        assertTrue(attacks.size == attacksById.size)
        assertTrue(attacksById.first().pokemonId == id)
    }
}