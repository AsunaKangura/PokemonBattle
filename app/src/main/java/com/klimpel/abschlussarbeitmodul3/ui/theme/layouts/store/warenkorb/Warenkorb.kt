package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.store.warenkorb

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.ui.components.TopAppBarTitelBackArrow
import com.klimpel.abschlussarbeitmodul3.ui.components.TopAppBarTitelBackArrowWarenkorb
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.pokemonbattlefinal.R

@Composable
fun WarenkorbScreen(navController: NavController) {

    Scaffold(
        topBar = { TopAppBarTitelBackArrowWarenkorb(R.string.titelwarenkorb, navController) },
        containerColor = LightBlueBackground,
    ) { innerpadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {
            Divider(thickness = 4.dp, color = LightBlue)

            ItemListe()
            Spacer(modifier = Modifier.height(30.dp))
            PreisSection(navController)

        }
    }
}


@Preview
@Composable
fun PreviewWarenkorb() {
    val context = LocalContext.current
    WarenkorbScreen(navController = NavController(context))
}

