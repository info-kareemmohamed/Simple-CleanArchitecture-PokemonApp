package com.example.simplecleanarchitecturepokemonapp.domain.use_case

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon
import com.example.simplecleanarchitecturepokemonapp.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonUesCase @Inject constructor(private val repository: PokemonRepository) {
    suspend operator fun invoke() = repository.getPokemonList()
     private val _pokemonList = MutableLiveData<List<Pokemon>>()
     val pokemonList:LiveData<List<Pokemon>> get() =  _pokemonList


}