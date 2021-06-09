package cl.littlephoenix.pokedex.data.repository

import cl.littlephoenix.pokedex.data.api.PokedexApiService
import cl.littlephoenix.pokedex.fakedata.TestUtil
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
class PokedexRepositoryTest {
    private lateinit var mockServer: MockWebServer
    private lateinit var pokedexRepository: PokedexRepository

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        val pokedexApiService = retrofit.create(PokedexApiService::class.java)
        pokedexRepository = PokedexRepository(pokedexApiService)
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun whenGetFirstGenerationPokemonThenResponseOkWithPokemonList() = runBlocking {
        val pokemonResponse = TestUtil.getPokemonResponse()
        val body = Gson().toJson(pokemonResponse)
        val mockResponse = MockResponse().setResponseCode(200).setBody(body)
        mockServer.enqueue(mockResponse)
        val pokemon = pokedexRepository.getFirstGenPokemon()
        assertTrue(pokemon.isSuccessful)
        assertNotNull(pokemon.body())
    }

    @Test
    fun whenGetPokemonDetailThenResponseOkWithPokemonDetail() = runBlocking {
        val id = 1
        val pokemonResponse = TestUtil.getPokemonInfoResponse()
        val body = Gson().toJson(pokemonResponse)
        val mockResponse = MockResponse().setResponseCode(200).setBody(body)
        mockServer.enqueue(mockResponse)
        val pokemon = pokedexRepository.getPokemonDetail(id)
        assertTrue(pokemon.isSuccessful)
        assertNotNull(pokemon.body())
    }

    @Test
    fun whenGetPokemonSpecieThenResponseOkWithPokemonSpecie() = runBlocking {
        val id = 1
        val pokemonResponse = TestUtil.getPokemonSpecieResponse()
        val body = Gson().toJson(pokemonResponse)
        val mockResponse = MockResponse().setResponseCode(200).setBody(body)
        mockServer.enqueue(mockResponse)
        val pokemon = pokedexRepository.getPokemonSpecie(id)
        assertTrue(pokemon.isSuccessful)
        assertNotNull(pokemon.body())
    }

    @Test
    fun whenGetPokemonEvolutionsThenResponseOkWithPokemonEvolutions() = runBlocking {
        val id = 1
        val pokemonResponse = TestUtil.getPokemonEvolutionResponse()
        val body = Gson().toJson(pokemonResponse)
        val mockResponse = MockResponse().setResponseCode(200).setBody(body)
        mockServer.enqueue(mockResponse)
        val pokemon = pokedexRepository.getPokemonEvolutions(id)
        assertTrue(pokemon.isSuccessful)
        assertNotNull(pokemon.body())
    }

    @Test
    fun whenGetPokemonLocationThenResponseOkWithPokemonLocation() = runBlocking {
        val id = 1
        val pokemonResponse = TestUtil.getArrayPokemonLocationsResponse()
        val body = Gson().toJson(pokemonResponse)
        val mockResponse = MockResponse().setResponseCode(200).setBody(body)
        mockServer.enqueue(mockResponse)
        val pokemon = pokedexRepository.getPokemonLocation(id)
        assertTrue(pokemon.isSuccessful)
        assertNotNull(pokemon.body())
    }

    @Test
    fun whenGetTypesThenResponseOkWithPokemonTypes() = runBlocking {
        val pokemonResponse = TestUtil.getTypeResponse()
        val body = Gson().toJson(pokemonResponse)
        val mockResponse = MockResponse().setResponseCode(200).setBody(body)
        mockServer.enqueue(mockResponse)
        val pokemon = pokedexRepository.getTypes()
        assertTrue(pokemon.isSuccessful)
        assertNotNull(pokemon.body())
    }
}