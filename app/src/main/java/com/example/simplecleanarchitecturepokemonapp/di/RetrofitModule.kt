package com.example.simplecleanarchitecturepokemonapp.di

import com.example.simplecleanarchitecturepokemonapp.common.Constants.BASE_URL
import com.example.simplecleanarchitecturepokemonapp.data.remote.PokemonApi
import com.example.simplecleanarchitecturepokemonapp.data.repository.PokemonRepositoryImpl
import com.example.simplecleanarchitecturepokemonapp.domain.repository.PokemonRepository
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
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(api)
    }


}