package com.example.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedexapp.data.remote.api.PokemonApiService
import com.example.pokedexapp.data.remote.repository.PokemonRepository
import com.example.pokedexapp.ui.theme.PokedexAppTheme
import com.example.pokedexapp.ui.theme.screens.detail.PokemonDetailScreen
import com.example.pokedexapp.ui.theme.screens.list.PokemonListScreen
import com.example.pokedexapp.ui.theme.screens.detail.PokemonDetailViewModel
import com.example.pokedexapp.ui.theme.screens.list.PokemonListViewModel
import com.example.pokedexapp.util.ViewModelFactory

class MainActivity : ComponentActivity() {

    private val api by lazy { PokemonApiService.api }
    private val repository by lazy { PokemonRepository(api) }
    private val viewModelFactory by lazy { ViewModelFactory(repository) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexAppTheme {
                PokedexApp(viewModelFactory)
            }
        }
    }
}

@Composable
fun PokedexApp(viewModelFactory: ViewModelFactory) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "pokemonList") {
        composable("pokemonList") {
            val viewModel: PokemonListViewModel = viewModel(factory = viewModelFactory)
            PokemonListScreen(
                viewModel = viewModel,
                onPokemonClick = { id -> navController.navigate("pokemonDetail/$id") }
            )
        }
        composable("pokemonDetail/{id}") { backStackEntry ->
            val viewModel: PokemonDetailViewModel = viewModel(factory = viewModelFactory)
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: return@composable
            LaunchedEffect(id) {
                viewModel.loadPokemonDetail(id)
            }
            PokemonDetailScreen(viewModel = viewModel)
        }
    }
}