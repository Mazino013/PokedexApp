package com.example.pokedexapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedexapp.data.remote.repository.PokemonRepository
import com.example.pokedexapp.ui.theme.screens.detail.PokemonDetailViewModel
import com.example.pokedexapp.ui.theme.screens.list.PokemonListViewModel

class ViewModelFactory(private val repository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonListViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(PokemonDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}