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
class SkillDaoTest {
    private lateinit var skillDao: SkillDao
    private lateinit var pokemonDatabase: PokemonDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        pokemonDatabase = Room.inMemoryDatabaseBuilder(
            context, PokemonDatabase::class.java).build()
        skillDao = pokemonDatabase.getSkillDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        pokemonDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetByPokemonID() = runBlocking {
        val skills = TestUtil.getSkill()
        skillDao.insert(skills)
        val id = 1
        val skillsById = skillDao.getSkillsByPokemon(id)
        assertTrue(skills.size == skillsById.size)
        assertTrue(skillsById.first().pokemonId == id)
    }
}