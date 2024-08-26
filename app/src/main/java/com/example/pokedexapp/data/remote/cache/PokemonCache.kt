package com.example.pokedexapp.data.remote.cache

import android.util.LruCache
import com.example.pokedexapp.data.remote.model.Pokemon


class PokemonCache {
        // LruCache for storing individual Pokemon details
        private val pokemonCache: LruCache<Int, Pokemon> = LruCache(100) // Cache size of 100 Pokemon

    // Cache for storing the full details of the Pokemon list
        private var fullPokemonList: List<Pokemon>? = null

        // Function to get a Pokemon by ID
        fun getPokemon(id: Int): Pokemon? {
            return pokemonCache.get(id)
        }

        // Function to add a Pokemon to the cache
        fun addPokemon(pokemon: Pokemon) {
            pokemonCache.put(pokemon.id, pokemon)
        }

    // Function to get the full details of the Pokemon list
        fun getFullPokemonList(): List<Pokemon>? {
            return fullPokemonList
        }


        // Function to set the full details of the Pokemon list
        fun setFullPokemonList(list: List<Pokemon>) {
            fullPokemonList = list
            // Also update the individual Pokemon cache
            list.forEach { addPokemon(it) }
        }

    }