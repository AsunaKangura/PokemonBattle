package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen

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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.viewmodels.ProfilViewModel
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel
import com.klimpel.pokemonbattlefinal.R


@OptIn(ExperimentalMaterial3Api::class)
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
                    ProfilInfosTopSection(navController)
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