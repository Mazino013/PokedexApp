package com.example.pokedexapp.ui.theme.screens.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokedexapp.util.ColorUtils

@Composable
fun PokemonDetailScreen(viewModel: PokemonDetailViewModel) {
    val pokemon by viewModel.pokemon.collectAsState()
    val scrollState = rememberScrollState()

    pokemon?.let { pokemonDetail ->
        val backgroundColor = ColorUtils.getPokemonTypeColor(pokemonDetail.types.firstOrNull() ?: "")

        Box(modifier = Modifier.fillMaxSize().background(backgroundColor)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                // Name and Types (top-left)
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Start)
                ) {
                    Text(
                        text = pokemonDetail.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    pokemonDetail.types.forEach { type ->
                        TypeChip(
                            type,
                            Modifier.padding(vertical = 4.dp)
                        )
                    }
                }

                // Pokemon Image (center)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    AsyncImage(
                        model = pokemonDetail.imageUrl,
                        contentDescription = pokemonDetail.name,
                        modifier = Modifier
                            .size(250.dp)
                            .align(Alignment.Center)
                    )
                }

                // Details and Description (rounded corner shape below)
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        // Other details
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "${pokemonDetail.weight / 10.0} kg",
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                Text("Weight", style = MaterialTheme.typography.titleMedium)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "${pokemonDetail.height / 10.0} m",
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                Text("Height", style = MaterialTheme.typography.titleMedium)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Stats
                        Text(
                            "Base Stats",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        pokemonDetail.stats.forEach { (stat, value) ->
                            StatBar(stat, value)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Description
                        Text(
                            "Description",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = pokemonDetail.description,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TypeChip(type: String, modifier: Modifier = Modifier) {
    val backgroundColor = ColorUtils.getPokemonTypeColor(type)
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.White),
        modifier = modifier.width(150.dp)
    ) {
        Text(
            text = type,
            color = Color.White,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun StatBar(statName: String, statValue: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = when (statName) {
                "special-attack" -> "Sp. Atk"
                "special-defense" -> "Sp. Def"
                else -> statName.replaceFirstChar { it.uppercase() }

            },
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier.width(80.dp)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(20.dp)
                .background(Color.LightGray, RoundedCornerShape(10.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth((statValue / 255f).coerceIn(0f, 1f))
                    .background(
                        when {
                            statValue < 50 -> Color.Red
                            statValue < 100 -> Color.Yellow
                            else -> Color.Green
                        },
                        RoundedCornerShape(10.dp)
                    )
            )
            Text(
                text = statValue.toString(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}