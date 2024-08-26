package com.example.pokedexapp.data.remote.repository

import com.example.pokedexapp.data.remote.api.PokemonApi
import com.example.pokedexapp.data.remote.api.PokemonSpeciesResponse
import com.example.pokedexapp.data.remote.cache.PokemonCache
import com.example.pokedexapp.data.remote.model.Pokemon
import com.example.pokedexapp.data.remote.model.PokemonDetailResponse

class PokemonRepository(private val api: PokemonApi) {

    private val cache = PokemonCache()

    suspend fun getPokemonList(): List<Pokemon> {
        // Check if the full list is in cache
        cache.getFullPokemonList()?.let { return it }

        val response = api.getPokemonList()
        val pokemonList = response.results.map { item ->
            val id = item.url.split("/").dropLast(1).last().toInt()
            getPokemonDetail(id)
        }

        // Cache the full list
        cache.setFullPokemonList(pokemonList)
        return pokemonList
    }

    suspend fun getPokemonDetail(id: Int): Pokemon {
        // Check if the Pokemon is in cache
        cache.getPokemon(id)?.let { return it }

        val response = api.getPokemonDetail(id)
        var pokemon = mapToPokemon(response)
        val speciesResponse = api.getPokemonSpecies(response.species.url)
        pokemon = pokemon.copy(description = getEnglishDescription(speciesResponse))

        // Cache the Pokemon
        cache.addPokemon(pokemon)

        return pokemon
    }

    private fun mapToPokemon(response: PokemonDetailResponse): Pokemon {
        return Pokemon(
            id = response.id,
            name = response.name,
            types = response.types.map { it.type.name },
            spriteUrl = response.sprites.front_default,
            weight = response.weight,
            height = response.height,
            order = response.order,
            stats = response.stats.associate { it.stat.name to it.base_stat },
            /*fetch this separately for detailed view*/
            description = ""
        )
    }

    private fun getEnglishDescription(speciesResponse: PokemonSpeciesResponse): String {
        return speciesResponse.flavor_text_entries
            .firstOrNull { it.language.name == "en" }
            ?.flavor_text
            ?.replace("\n", " ")
            ?: "No description available."
    }

}