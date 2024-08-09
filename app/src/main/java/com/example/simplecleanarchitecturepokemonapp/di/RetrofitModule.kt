package com.example.simplecleanarchitecturepokemonapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.simplecleanarchitecturepokemonapp.common.Constants.BASE_URL
import com.example.simplecleanarchitecturepokemonapp.common.Constants.DATABASE_NAME
import com.example.simplecleanarchitecturepokemonapp.data.local.PokemonDao
import com.example.simplecleanarchitecturepokemonapp.data.local.PokemonDatabase
import com.example.simplecleanarchitecturepokemonapp.data.remote.PokemonApi
import com.example.simplecleanarchitecturepokemonapp.data.repository.PokemonRepositoryImpl
import com.example.simplecleanarchitecturepokemonapp.domain.repository.PokemonRepository
import com.example.simplecleanarchitecturepokemonapp.domain.use_case.AddPokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi,dao: PokemonDao): PokemonRepository {
        return PokemonRepositoryImpl(api,dao)
    }


    @Provides
    @Singleton
    fun providePokemonDatabase(context: Application): PokemonDatabase {
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(database: PokemonDatabase):PokemonDao{
        return database.dao
    }
    @Provides
    fun provideAddPokemonUseCase(repository: PokemonRepository): AddPokemonUseCase {
        return AddPokemonUseCase(repository)
    }
}