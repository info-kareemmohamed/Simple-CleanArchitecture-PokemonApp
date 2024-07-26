package com.example.simplecleanarchitecturepokemonapp.data.remote.dto

import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon

data class PokemonDto(
    val name: String,
    val url: String
)

fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        name = name,
        url = url
    )
}