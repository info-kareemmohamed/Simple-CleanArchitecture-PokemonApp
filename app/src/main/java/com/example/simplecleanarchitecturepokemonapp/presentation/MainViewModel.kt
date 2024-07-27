package com.example.simplecleanarchitecturepokemonapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.collect
import androidx.lifecycle.viewModelScope
import com.example.simplecleanarchitecturepokemonapp.common.Resource
import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon
import com.example.simplecleanarchitecturepokemonapp.domain.use_case.GetPokemonUesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getPokemonUesCase: GetPokemonUesCase) : ViewModel() {

    init {
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

    private fun getPokemonList() {
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

