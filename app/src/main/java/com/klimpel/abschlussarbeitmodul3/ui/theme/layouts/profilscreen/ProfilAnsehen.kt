package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.viewmodels.ProfilViewModel
import com.klimpel.pokemonbattlefinal.R



@Composable
fun ProfilScreen(
    navController: NavController,
    viewModelprofil: ProfilViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBarTitelBackArrowProfil(
                pageTitle = R.string.titelProfil,
                navController = navController
            )
        },
        containerColor = LightBlueBackground,
    ) { innerpadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {
            Divider(thickness = 4.dp, color = LightBlue)

            // Profil Infos
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.20f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight()
                ) {
                    ProfilInfosTopSection()
                }
            }
            Divider(thickness = 1.dp, color = LightBlue)

            // Eigenes Team
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
            ) {
                EigeneTeamSection()
            }
            Divider(thickness = 1.dp, color = LightBlue)

            // Team erstellen Button
            Spacer(modifier = Modifier.height(15.dp))
            if (viewModelprofil.currentUser?.teams != 10) {
                TeamErstellenButton(navController)
            } else {
                TeamErstellenButtonDisabeled()
            }
            Spacer(modifier = Modifier.height(15.dp))


            // Team Übersicht LazyColumn
            if (viewModelprofil.currentUser?.teams != 0) {
                // TeamÜbersicht
               ProfilTeamUbersicht(navController)
            } else {
                Spacer(modifier = Modifier.height(160.dp))
                NoCustomTeams()
            }


        }
    }
}