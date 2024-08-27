package com.example.pokedexapp.util

import androidx.compose.ui.graphics.Color

object ColorUtils {

    fun getPokemonTypeColor(type: String): Color {
        return when (type.lowercase()) {
            "grass" -> Color(0xFF78C850)
            "fire" -> Color(0xFFF08030)
            "water" -> Color(0xFF6890F0)
            "electric" -> Color(0xFFF7D02C)
            "poison" -> Color(0xFFA33EA1)
            "normal" -> Color(0xFFA8A878)
            "flying" -> Color(0xFFA890F0)
            "ground" -> Color(0xFFE2BF65)
            "rock" -> Color(0xFFB6A136)
            "bug" -> Color(0xFFA6B91A)
            "ghost" -> Color(0xFF735797)
            "steel" -> Color(0xFFB7B7CE)
            "psychic" -> Color(0xFFF85888)
            "ice" -> Color(0xFF96D9D6)
            "dragon" -> Color(0xFF6F35FC)
            "dark" -> Color(0xFF705746)
            "fairy" -> Color(0xFFD685AD)
            "fighting" -> Color(0xFFC22E28)
            else -> Color.Gray
        }
    }

    fun getPokemonTypeColorWithAlpha(type: String, alpha: Float): Color {
        return getPokemonTypeColor(type).copy(alpha = alpha)
    }
}