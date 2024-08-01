package com.example.simplecleanarchitecturepokemonapp.domain.use_case

import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon
import com.example.simplecleanarchitecturepokemonapp.domain.repository.PokemonRepository
import javax.inject.Inject

class DeletePokemonUseCase @Inject constructor(private val repository: PokemonRepository) {
suspend operator fun invoke(pokemon: Pokemon) = repository.deletePokemon(pokemon)
}