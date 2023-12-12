package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.meinepokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.ui.components.TopAppBarTitelBackArrow
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily
import com.klimpel.pokemonbattlefinal.R

@Composable
fun NoOwnedPokemon(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBarTitelBackArrow(
                pageTitle = R.string.titelmeinepokemon,
                navController = navController
            )
        },
        containerColor = LightBlueBackground,
    ) { innerpadding ->
        Divider(
            thickness = 4.dp,
            color = LightBlue,
            modifier = Modifier.padding(innerpadding)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {
            Text(
                text = "Du hast noch keine",
                fontSize = 28.sp,
                color = LightBlue,
                fontFamily = pokemonFontFamily
            )
            Text(
                text = "eigenen Pokemon",
                fontSize = 28.sp,
                color = LightBlue,
                fontFamily = pokemonFontFamily
            )
        }
    }
}