package com.example.simplecleanarchitecturepokemonapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(vararg pokemon: Pokemon )

    @Delete
    suspend fun deletePokemon(vararg pokemon: Pokemon):Int

    @Query("SELECT * FROM pokemon")
    fun getPokemonList():Flow<List<Pokemon>>


}