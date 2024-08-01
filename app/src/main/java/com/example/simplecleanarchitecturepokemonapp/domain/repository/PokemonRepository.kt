package com.example.simplecleanarchitecturepokemonapp.domain.repository

import com.example.simplecleanarchitecturepokemonapp.common.Resource
import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
     fun getPokemonList(): Flow<Resource<List<Pokemon>>>
    suspend fun addPokemon(pokemon: Pokemon)
    suspend fun deletePokemon(pokemon: Pokemon)
}