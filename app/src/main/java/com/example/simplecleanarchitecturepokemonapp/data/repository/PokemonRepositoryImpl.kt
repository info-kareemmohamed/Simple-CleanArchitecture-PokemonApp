package com.example.simplecleanarchitecturepokemonapp.data.repository

import com.example.simplecleanarchitecturepokemonapp.common.Resource
import com.example.simplecleanarchitecturepokemonapp.data.local.PokemonDao
import com.example.simplecleanarchitecturepokemonapp.data.remote.PokemonApi
import com.example.simplecleanarchitecturepokemonapp.data.remote.dto.toPokemon
import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon
import com.example.simplecleanarchitecturepokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi,
    private val dao: PokemonDao
) : PokemonRepository {
    override fun getPokemonList(): Flow<Resource<List<Pokemon>>> = flow {
        try {
            emit(Resource.Loading())
            val pokemonList = api.getPokemonList()
            val pokemonDomainList = pokemonList.results.map { it.toPokemon() }
            emit(Resource.Success(pokemonDomainList))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    override suspend fun addPokemon(pokemon: Pokemon) = dao.insertPokemon(pokemon)


    override suspend fun deletePokemon(pokemon: Pokemon) {
        dao.deletePokemon(pokemon)
    }


}