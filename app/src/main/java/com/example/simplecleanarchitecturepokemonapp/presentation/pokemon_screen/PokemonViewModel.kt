package com.example.simplecleanarchitecturepokemonapp.presentation.pokemon_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon

class PokemonViewModel : ViewModel() {

  private  val _pokemon= MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon


  fun setPokemon(pokemon: Pokemon) {
    _pokemon.value = pokemon
  }




}