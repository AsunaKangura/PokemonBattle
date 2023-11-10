package com.klimpel.abschlussarbeitmodul3

import LoginScreen
import Pokedex
import PokemonDetailScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.meinepokemon.MeinePokemon
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.TeamBearbeitenScreen
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.TeamErstellenScreen
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.TeamScreen
import com.klimpel.pokemonbattlefinal.ui.theme.layouts.HomeScreen
import com.klimpel.pokemonbattlefinal.ui.theme.layouts.RegisterScreen
import com.klimpel.pokemonbattlefinal.ui.theme.layouts.SplashScreen
import com.klimpel.pokemonbattlefinal.ui.theme.layouts.WelcomeScreen
import java.util.Locale

@Composable
fun NavigationAppStart(navController: NavHostController){

    NavHost(navController = navController, startDestination = "LoginScreen"){

        composable(Screen.Register.route) { RegisterScreen(navController) }
        composable(Screen.WelcomeScreen.route) { WelcomeScreen(navController) }
        composable(Screen.SplashOut.route) { SplashScreen(navController) }
        composable(Screen.LoginScreen.route) { LoginScreen(navController) }
        composable(Screen.HomeScreen.route) { HomeScreen(navController) }
        composable(Screen.Pokedex.route) { Pokedex(navController) }
        composable(Screen.Teamubersicht.route) { TeamScreen(navController) }
        composable(Screen.MeinePokemon.route) { MeinePokemon(navController) }
        composable(Screen.Teamerstellen.route) { TeamErstellenScreen(navController) }
        composable(Screen.Teambearbeiten.route) { TeamBearbeitenScreen(navController) }
        composable(
            Screen.PokemonDetailScreen.route,
            arguments = listOf(
                navArgument("pokemonName"){
                    type = NavType.StringType
                }
            )
        ){
            val pokemonName = remember {
                it.arguments?.getString("pokemonName")
            }
            PokemonDetailScreen(
                dominantColor = LightBlueBackground,
                pokemonName = pokemonName?.lowercase(Locale.ROOT) ?: "",
                navController = navController
            )
        }
    }
}
