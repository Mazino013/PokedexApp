package com.example.pokedexapp.data.remote.cache

import android.content.Context
import android.util.Log
import android.util.LruCache
import com.example.pokedexapp.model.Pokemon
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class PokemonCache @Inject constructor(
    @ApplicationContext private val context: Context
) {
    // LruCache for storing individual Pokemon details
    // Cache size of 100 Pokemon
    private val pokemonCache: LruCache<Int, Pokemon> = LruCache(100)

    // Cache for storing the full details of the Pokemon list
    private var fullPokemonList: List<Pokemon>? = null

    // Function to get a Pokemon by ID
    fun getPokemon(id: Int): Pokemon? {
        val cachedPokemon = pokemonCache.get(id)
        if (cachedPokemon != null) {
            Log.d("PokemonCache", "Returning cached Pokemon: ${cachedPokemon.name}")
        } else {
            Log.d("PokemonCache", "Pokemon with id $id not found in cache")
        }
        return cachedPokemon
    }

    // Function to add a Pokemon to the cache
    fun addPokemon(pokemon: Pokemon) {
        pokemonCache.put(pokemon.id, pokemon)
        Log.d("PokemonCache", "Added Pokemon to cache: ${pokemon.name}")
    }

    // Function to get the full details of the Pokemon list
    fun getFullPokemonList(): List<Pokemon>? {
        if (fullPokemonList != null) {
            Log.d("PokemonCache", "Returning cached Pokemon list")
        } else {
            Log.d("PokemonCache", "Full Pokemon list not found in cache")
        }
        return fullPokemonList
    }

    // Function to set the full details of the Pokemon list
    fun setFullPokemonList(list: List<Pokemon>) {
        fullPokemonList = list
        Log.d("PokemonCache", "Set full Pokemon list in cache, size: ${list.size}")
        // Also update the individual Pokemon cache
        list.forEach { addPokemon(it) }
    }
}