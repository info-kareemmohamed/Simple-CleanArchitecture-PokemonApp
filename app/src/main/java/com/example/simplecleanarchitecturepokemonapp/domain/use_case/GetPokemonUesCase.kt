package com.example.simplecleanarchitecturepokemonapp.domain.use_case


import com.example.simplecleanarchitecturepokemonapp.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonUesCase @Inject constructor(private val repository: PokemonRepository) {
     operator fun invoke() = repository.getPokemonList()



}