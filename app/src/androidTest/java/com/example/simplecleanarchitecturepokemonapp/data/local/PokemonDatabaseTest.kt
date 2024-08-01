package com.example.simplecleanarchitecturepokemonapp.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


class PokemonDatabaseTest {

    private lateinit var database: PokemonDatabase
    private lateinit var dao: PokemonDao

    @Before
    fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, PokemonDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.dao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertAndGetPokemon() = runBlocking {
        val pokemon = Pokemon(name = "Pikachu", url = "http://example.com/pikachu")
        dao.insertPokemon(pokemon)
        val pokemons = dao.getPokemonList().first()

        assertNotNull(pokemons)
        assertEquals(1, pokemons.size)
        assertEquals("Pikachu", pokemons[0].name)
    }



}