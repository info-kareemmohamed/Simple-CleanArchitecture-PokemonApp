package com.example.simplecleanarchitecturepokemonapp.presentation.home_screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.simplecleanarchitecturepokemonapp.common.Resource
import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon
import com.example.simplecleanarchitecturepokemonapp.domain.use_case.GetPokemonUesCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MainViewModel
    private lateinit var getPokemonUseCase: GetPokemonUesCase

    @Before
    fun setup() {
        getPokemonUseCase = mockk()
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(getPokemonUseCase)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getPokemonList returns loading, progressBar should be true`() = runTest {
        // Given
        coEvery { getPokemonUseCase.invoke() } returns flow {
            emit(Resource.Loading())
        }

        // When
        viewModel.getPokemonList()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(viewModel.progressBar.value)
    }
    @Test
    fun `when getPokemonList returns success, pokemonList should be updated`() = runTest {
        // Given
        val pokemonList = listOf(Pokemon("Pikachu", "Electric"))
        coEvery { getPokemonUseCase.invoke() } returns flow {
            emit(Resource.Success(pokemonList))
        }

        // When
        viewModel.getPokemonList()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
      assertTrue(viewModel.pokemonList.value == pokemonList)
    }

    @Test
    fun `when getPokemonList returns error, messageError and flagError should be updated`() = runTest {
        // Given
        val errorMessage = "An error occurred"
        coEvery { getPokemonUseCase.invoke() } returns flow {
            emit(Resource.Error(errorMessage))
        }

        // When
        viewModel.getPokemonList()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(viewModel.messageError.value == errorMessage)
        assert(viewModel.flagError.value)
    }
}