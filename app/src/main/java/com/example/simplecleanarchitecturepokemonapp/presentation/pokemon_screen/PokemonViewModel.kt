package com.example.simplecleanarchitecturepokemonapp.presentation.pokemon_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class PokemonViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle
) : ViewModel() {
  private  val _pokemon= MutableLiveData<Pokemon>()
  val pokemon: LiveData<Pokemon> = _pokemon

  init {

    _pokemon.postValue(savedStateHandle.get<Pokemon>("pokemon"))
  }





}