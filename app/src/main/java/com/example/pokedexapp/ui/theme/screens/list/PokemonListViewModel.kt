package com.example.pokedexapp.ui.theme.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.model.Pokemon
import com.example.pokedexapp.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    private val pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    private val _isLoading = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)
    private val _selectedTypes = MutableStateFlow<Set<String>>(emptySet())

    val isLoading: StateFlow<Boolean> = _isLoading
    val error: StateFlow<String?> = _error
    val selectedTypes: StateFlow<Set<String>> = _selectedTypes

    val filteredPokemonList: StateFlow<List<Pokemon>> = combine(pokemonList, _selectedTypes) { list, filters ->
        when {
            filters.isEmpty() -> list
            else -> list.filter { pokemon -> pokemon.types.any { it in filters } }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val availableTypes: StateFlow<List<String>> = pokemonList.map { list ->
        list.flatMap { it.types }.distinct().sorted()
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        loadPokemonList()
    }

    private fun loadPokemonList() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                pokemonList.value = repository.getPokemonList()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleFilterType(type: String) {
        _selectedTypes.update { currentFilters ->
            when {
                type in currentFilters -> currentFilters - type
                currentFilters.size < 2 -> currentFilters + type
                else -> (currentFilters - currentFilters.first()) + type
            }
        }
    }

    fun clearFilters() {
        _selectedTypes.value = emptySet()
    }
}