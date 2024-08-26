package com.example.pokedexapp.data.remote.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val types: String,
    val spriteUrl: String,
    val weight: Int,
    val height: Int,
    val order: Int,
    val stats: String,
    val description: String
)