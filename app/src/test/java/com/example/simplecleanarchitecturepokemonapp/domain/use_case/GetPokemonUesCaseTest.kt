package com.example.simplecleanarchitecturepokemonapp.domain.use_case

import com.example.simplecleanarchitecturepokemonapp.common.Resource
import com.example.simplecleanarchitecturepokemonapp.domain.repository.FakePokemonRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetPokemonUesCaseTest {


    private lateinit var getPokemonUseCase: GetPokemonUesCase

    @Before
    fun setUp() {
        // Initialize with a default fake repository that does not return errors
        getPokemonUseCase = GetPokemonUesCase(FakePokemonRepository())
    }

    @Test
    fun `invoke returns success`() = runTest {
        val result = getPokemonUseCase().toList()

        assert(result[0] is Resource.Loading)
        assert(result[1] is Resource.Success)
        val successResource = result[1] as Resource.Success
        val data = successResource.data
        assertEquals(3, data?.size)
        assertEquals("Bulbasaur", data?.get(0)?.name)
        assertEquals("Charmander", data?.get(1)?.name)
        assertEquals("Squirtle", data?.get(2)?.name)
    }

    @Test
    fun `invoke returns error`() = runTest {
        // Initialize with a fake repository that returns an error
        getPokemonUseCase = GetPokemonUesCase(FakePokemonRepository(true))

        val result = getPokemonUseCase().toList()

        assert(result[0] is Resource.Loading)
        assert(result[1] is Resource.Error)
        val errorResource = result[1] as Resource.Error
        assertEquals("An unexpected error occurred", errorResource.message)
    }
}