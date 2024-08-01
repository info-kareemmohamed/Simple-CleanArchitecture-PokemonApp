package com.example.simplecleanarchitecturepokemonapp.presentation.home_screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplecleanarchitecturepokemonapp.common.Resource
import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon
import com.example.simplecleanarchitecturepokemonapp.domain.use_case.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getPokemonUesCase: GetPokemonUseCase) :
    ViewModel() {

    init {
        // Comment this line if you want to manually test the ViewModel without automatically triggering the data fetch.
        getPokemonList()
    }

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    private val _messageError = MutableStateFlow<String>("")
    val messageError: StateFlow<String> = _messageError

    private val _flagError = MutableStateFlow<Boolean>(false)
    val flagError: StateFlow<Boolean> = _flagError

    private val _progressBar = MutableStateFlow<Boolean>(false)
    val progressBar: StateFlow<Boolean> = _progressBar

    fun getPokemonList() {
        viewModelScope.launch {
            getPokemonUesCase().collect { result ->
                when (result) {
                    is Resource.Loading -> _progressBar.value = true
                    is Resource.Success -> {
                        _progressBar.value = false
                        _pokemonList.value = result.data ?: emptyList()
                    }

                    is Resource.Error -> {
                        _progressBar.value = false
                        _flagError.value = true
                        _messageError.value = result.message ?: "An unexpected error occurred"
                    }
                }
            }
        }
    }
}

