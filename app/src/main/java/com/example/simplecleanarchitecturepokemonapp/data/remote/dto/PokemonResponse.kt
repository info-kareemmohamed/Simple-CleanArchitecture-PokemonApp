package com.example.simplecleanarchitecturepokemonapp.data.remote.dto

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonDto>

)
