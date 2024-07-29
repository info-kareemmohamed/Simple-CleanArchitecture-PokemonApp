package com.example.simplecleanarchitecturepokemonapp.domain.repository

import com.example.simplecleanarchitecturepokemonapp.common.Resource
import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokemonRepository (private val shouldReturnError: Boolean = false): PokemonRepository {



    override fun getPokemonList(): Flow<Resource<List<Pokemon>>> =flow {
        emit(Resource.Loading())
        if (shouldReturnError) {
            emit(Resource.Error("An unexpected error occurred"))
        } else {
            emit(Resource.Success(listOf(
                Pokemon("Bulbasaur",""),
                Pokemon("Charmander",""),
                Pokemon("Squirtle","")
            )))
        }
    }

}