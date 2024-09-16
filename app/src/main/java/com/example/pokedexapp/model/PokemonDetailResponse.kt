package com.example.pokedexapp.model

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val types: List<TypeSlot>,
    val sprites: Sprites,
    val weight: Int,
    val height: Int,
    val order: Int,
    val stats: List<Stat>,
    val species: Species
)

data class TypeSlot(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String,
    val url: String
)

data class Sprites(
    val front_default: String
)

data class Stat(
    val base_stat: Int,
    val stat: StatInfo
)

data class StatInfo(
    val name: String
)

data class Species(
    val url: String
)