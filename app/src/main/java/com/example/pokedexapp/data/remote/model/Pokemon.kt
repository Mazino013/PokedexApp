package com.example.pokedexapp.data.remote.model

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<String>,
    val spriteUrl: String,
    val weight: Int,
    val height: Int,
    val order: Int,
    val stats: Map<String, Int>,
    val description: String = "" //Fetch Separately
)