package com.example.simplecleanarchitecturepokemonapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon

@Database(
    entities = [Pokemon::class],
    version = 1,
    exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase() {
abstract val dao: PokemonDao
}