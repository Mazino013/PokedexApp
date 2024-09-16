package com.example.pokedexapp.model

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<String>,
    val imageUrl: String,
    val weight: Int,
    val height: Int,
    val order: Int,
    val stats: Map<String, Int>,
    val description: String = "" //Fetch Separately
)