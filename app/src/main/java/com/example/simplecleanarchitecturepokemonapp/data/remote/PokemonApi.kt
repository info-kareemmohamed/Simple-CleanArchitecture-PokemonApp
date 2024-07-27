package com.example.simplecleanarchitecturepokemonapp.data.remote

import com.example.simplecleanarchitecturepokemonapp.data.remote.dto.PokemonResponse
import retrofit2.http.GET

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonResponse
}