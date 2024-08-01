package com.example.simplecleanarchitecturepokemonapp.domain.use_case


import com.example.simplecleanarchitecturepokemonapp.domain.repository.PokemonRepository
import javax.inject.Inject

/**
 * Use case for getting a list of Pokemon from the repository.
 *
 * The result is wrapped in a Resource which represents the state of the data:
 * ... Resource.Loading indicates that the data is currently being loaded.
 * ... Resource.Success contains the list of Pokemon if the data is loaded successfully.
 * ... Resource.Error contains an error message if the data failed to load.
 *
 * @param repository The repository to fetch the Pokemon list from.
 */

class GetPokemonUseCase @Inject constructor(private val repository: PokemonRepository) {
     operator fun invoke() = repository.getPokemonList()
}