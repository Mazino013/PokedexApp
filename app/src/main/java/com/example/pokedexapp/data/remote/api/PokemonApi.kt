package com.example.pokedexapp.data.remote.api

import com.example.pokedexapp.data.remote.model.PokemonDetailResponse
import com.example.pokedexapp.data.remote.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int = 100): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): PokemonDetailResponse

    @GET
    suspend fun getPokemonSpecies(@Url url: String): PokemonSpeciesResponse
}

data class PokemonSpeciesResponse(
    val flavor_text_entries: List<FlavorTextEntry>
)

data class FlavorTextEntry(
    val flavor_text: String,
    val language: Language
)

data class Language(
    val name: String
)