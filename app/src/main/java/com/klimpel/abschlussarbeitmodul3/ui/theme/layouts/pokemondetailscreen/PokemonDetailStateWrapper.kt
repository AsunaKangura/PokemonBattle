package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.pokemondetailscreen

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.klimpel.abschlussarbeitmodul3.util.Resource

@Composable
fun PokemonDetailStateWrapper(
    pokemonInfo: Resource<Pokemon>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier
) {
    when(pokemonInfo) {
        is Resource.Success -> {
            pokemonInfo.data?.let {
                PokemonDetailSection(
                    pokemonInfo = it,
                    modifier = modifier
                        .offset(y = (-20).dp)
                )
            }
        }
        is Resource.Error -> {
            pokemonInfo.message?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = modifier
                )
            }
        }
        is Resource.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = loadingModifier
            )
        }

        else -> {}
    }
}