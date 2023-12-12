package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonGrindEntry

@Composable
fun AvailablePokemonRowEditTeam(
    rowIndex: Int,
    entries: List<PokemonGrindEntry>,
) {
    Column {
        Row {
            AvailablePokemonListItemEditTeam(
                pokemon = entries[rowIndex],
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}