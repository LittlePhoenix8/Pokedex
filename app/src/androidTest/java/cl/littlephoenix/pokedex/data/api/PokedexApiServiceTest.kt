package cl.littlephoenix.pokedex.data.api

import cl.littlephoenix.pokedex.fakedata.TestUtil
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
class PokedexApiServiceTest {
    private lateinit var mockWebService: MockWebServer
    private lateinit var pokedexApiService: PokedexApiService

    @Before
    fun setUp() {
        mockWebService = MockWebServer()
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebService.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        pokedexApiService = retrofit.create(PokedexApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebService.shutdown()
    }

    @Test
    fun testGetFirstGenPokemon() = runBlocking {
        val pokemonResponse = TestUtil.getPokemonResponse()
        val body = Gson().toJson(pokemonResponse)
        val mockResponse = MockResponse().setResponseCode(200).setBody(body)
        mockWebService.enqueue(mockResponse)
        val pokemon = pokedexApiService.getFirstGenPokemon()
        assertTrue(pokemon.isSuccessful)
        assertNotNull(pokemon.body())
    }
}