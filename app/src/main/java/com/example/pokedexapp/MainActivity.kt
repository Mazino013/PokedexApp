package com.example.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedexapp.ui.theme.PokedexAppTheme
import com.example.pokedexapp.ui.theme.screens.detail.PokemonDetailScreen
import com.example.pokedexapp.ui.theme.screens.list.PokemonListScreen
import com.example.pokedexapp.ui.theme.screens.detail.PokemonDetailViewModel
import com.example.pokedexapp.ui.theme.screens.list.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexAppTheme {
                PokedexApp()
            }
        }
    }
}

@Composable
fun PokedexApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "pokemonList") {
        composable("pokemonList") {
            val viewModel: PokemonListViewModel = hiltViewModel()
            PokemonListScreen(
                viewModel = viewModel,
                onPokemonClick = { id -> navController.navigate("pokemonDetail/$id") }
            )
        }
        composable("pokemonDetail/{id}") { backStackEntry ->
            val viewModel: PokemonDetailViewModel = hiltViewModel()
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: return@composable
            LaunchedEffect(id) {
                viewModel.loadPokemonDetail(id)
            }
            PokemonDetailScreen(viewModel = viewModel)
        }
    }
}