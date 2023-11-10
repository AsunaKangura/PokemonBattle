package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.data.models.PokedexListEntry
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonGrindEntry
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profil.ListItemAvatar

@Composable
fun AvailablePokemonRow(
    navController: NavController,
    context: Context,
    clickedID: Int,
    rowIndex: Int,
    entries: List<PokemonGrindEntry>,
    modifier: Modifier = Modifier,
) {
    Column {
        Row {
            AvailablePokemonListItem(
                navController= navController,
                context= context,
                clickid = clickedID,
                pokemon = entries[rowIndex],
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Composable
fun AvailablePokemonRowEditTeam(
    navController: NavController,
    context: Context,
    clickedID: Int,
    rowIndex: Int,
    entries: List<PokemonGrindEntry>,
    modifier: Modifier = Modifier,
) {
    Column {
        Log.e("ROW","$clickedID")
        Row {
            AvailablePokemonListItemEditTeam(
                navController= navController,
                context= context,
                clickid = clickedID,
                pokemon = entries[rowIndex],
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}