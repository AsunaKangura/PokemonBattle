package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.pokedexscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.data.models.PokedexListEntry
import com.klimpel.abschlussarbeitmodul3.ui.components.CardWithAnimatedBorder
import com.klimpel.abschlussarbeitmodul3.ui.components.swipeableelements.SwipeableCardLeft
import com.klimpel.abschlussarbeitmodul3.util.pokemonEvoloutionBorder


@Composable
fun PokedexRow(
    rowIndex: Int,
    entries: List<PokedexListEntry>,
    navController: NavController,
) {
    Column {
        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 0.dp
            ),
        ) {
            val route = "PokemonDetailScreen/${entries[rowIndex].pokemonName}"
            SwipeableCardLeft(navController = navController, route = route) {
                CardWithAnimatedBorder(
                    borderColors = pokemonEvoloutionBorder(entries[rowIndex].pokemonName),
                ) {
                    PokemonCard(
                        entry = entries[rowIndex],
                        navController = navController,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}