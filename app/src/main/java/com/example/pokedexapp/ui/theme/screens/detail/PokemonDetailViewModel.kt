package com.example.pokedexapp.ui.theme.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.remote.model.Pokemon
import com.example.pokedexapp.data.remote.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val repository: PokemonRepository) : ViewModel() {
    private val _pokemon = MutableStateFlow<Pokemon?>(null)
    val pokemon: StateFlow<Pokemon?> = _pokemon.asStateFlow()

    fun loadPokemonDetail(id: Int) {
        viewModelScope.launch {
            try {
                _pokemon.value = repository.getPokemonDetail(id)
            } catch (e: Exception) {

                Log.e("PokemonDetailViewModel", "Error loading Pokemon: ${e.message}")
            }
        }
    }
}