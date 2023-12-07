package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarTitelBackArrowTeamSeite(
    pageTitle: String,
    navController: NavController,
    viewModelteam: TeamViewModel = hiltViewModel()
) {

    val showMenu = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = pageTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = LightBlue,
                fontWeight = FontWeight.Bold,
                fontFamily = pokemonFontFamily,
            )
        },

        navigationIcon = {
            IconButton(
                onClick = {
                    //viewModelteam.deleteCurrentTeam()
                    scope.launch {
                        delay(1000)
                        navController.navigate(Screen.ProfilScreen.route)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Arrow",
                    tint = LightBlue
                )
            }
        },
    )
}