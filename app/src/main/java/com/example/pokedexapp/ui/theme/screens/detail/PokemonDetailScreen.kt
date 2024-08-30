package com.example.pokedexapp.ui.theme.screens.detail

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokedexapp.util.ColorUtils

@Composable
fun PokemonDetailScreen(viewModel: PokemonDetailViewModel) {
    val pokemon by viewModel.pokemon.collectAsState()
    val scrollState = rememberScrollState()

    pokemon?.let { pokemonData ->
        val backgroundColor = ColorUtils.getPokemonTypeColor(pokemonData.types.firstOrNull() ?: "")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Colored accent behind the sprite
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(backgroundColor)
            ) {
                AsyncImage(
                    model = pokemonData.imageUrl,
                    contentDescription = pokemonData.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .align(Alignment.Center)
                        .background(Color.White.copy(alpha = 0.5f))
                        .padding(bottom = 16.dp),
                    contentScale = ContentScale.Fit
                )
            }

            // Pokemon details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Name
                Text(
                    text = pokemonData.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Types
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    pokemonData.types.forEach { type ->
                        TypeChip(type, Modifier.padding(horizontal = 4.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Other details
                DetailItem("Height", "${pokemonData.height / 10.0} m")
                DetailItem("Weight", "${pokemonData.weight / 10.0} kg")

                Spacer(modifier = Modifier.height(16.dp))

                // Stats
//                Text(
//                    "Base Stats",
//                    style = MaterialTheme.typography.titleSmall,
//                    fontWeight = FontWeight.Bold
//                )
//                pokemonData.stats.forEach { (stat, value) ->
//                    StatBar(stat, value)
//                }

                Text(
                    "Base Stats",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                pokemonData.stats.forEach { (stat, value) ->
                    StatBar(stat, value)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Description
                Text(
                    "Description",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = pokemonData.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun TypeChip(type: String, modifier: Modifier = Modifier) {
    val backgroundColor = ColorUtils.getPokemonTypeColor(type)
    Surface(
        color = backgroundColor,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Text(
            text = type,
            color = Color.White,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            fontSize = 12.sp
        )
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Text(text = value)
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