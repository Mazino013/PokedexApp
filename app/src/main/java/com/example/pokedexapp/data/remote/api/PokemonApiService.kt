package com.example.pokedexapp.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonApiService {

    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val api: PokemonApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }
}