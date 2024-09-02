package com.example.pokedexapp.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedexapp.data.remote.model.Pokemon
import com.example.pokedexapp.util.ColorUtils


@Composable
fun PokemonCard(pokemon: Pokemon, onClick: () -> Unit) {
    val backgroundColor = ColorUtils.getPokemonTypeColor(pokemon.types.firstOrNull() ?: "")
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            // Pokemon image
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.Center)
                    .padding(8.dp)
            )

            // Order number at the top right
            Text(

                text = "No:${pokemon.order}",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(5.dp),
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )

            // Pokemon name and type at the bottom
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
