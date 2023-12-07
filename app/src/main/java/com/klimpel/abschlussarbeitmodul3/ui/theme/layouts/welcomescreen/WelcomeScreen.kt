package com.klimpel.pokemonbattlefinal.ui.theme.layouts


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.welcomescreen.WelcomeScreenContent
import com.klimpel.abschlussarbeitmodul3.util.Contants
import com.klimpel.abschlussarbeitmodul3.viewmodels.ProfilViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(navController: NavController, viewModel: ProfilViewModel = hiltViewModel()) {

    if (Contants.auth.currentUser?.uid == null) {
        WelcomeScreenContent(navController = navController)
    } else {
        Contants.auth.uid?.let { viewModel.updateCurrentUser(it) }
        LaunchedEffect(key1 = "key") {
            delay(1500)
            navController.navigate(Screen.HomeScreen.route)
        }
    }
}