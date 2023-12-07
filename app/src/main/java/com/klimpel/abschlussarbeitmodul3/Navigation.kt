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
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen.ProfilScreen
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen.rucksack.RucksackScreen
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.store.StoreScreen
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.store.produktdetail.ProduktScreen
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.store.warenkorb.WarenkorbScreen
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teambearbeiten.TeamBearbeitenScreen
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teamerstellen.TeamErstellenScreen
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.TeamPage
import com.klimpel.pokemonbattlefinal.ui.theme.layouts.HomeScreen
import com.klimpel.pokemonbattlefinal.ui.theme.layouts.RegisterScreen
import com.klimpel.pokemonbattlefinal.ui.theme.layouts.SplashScreen
import com.klimpel.pokemonbattlefinal.ui.theme.layouts.WelcomeScreen
import java.util.Locale

@Composable
fun NavigationAppStart(navController: NavHostController){
    NavHost(navController = navController, startDestination = "SplashOut"){
        composable(Screen.Register.route) { RegisterScreen(navController) }
        composable(Screen.WelcomeScreen.route) { WelcomeScreen(navController) }
        composable(Screen.SplashOut.route) { SplashScreen(navController) }
        composable(Screen.LoginScreen.route) { LoginScreen(navController) }
        composable(Screen.HomeScreen.route) { HomeScreen(navController) }
        composable(Screen.Pokedex.route) { Pokedex(navController) }
        composable(Screen.MeinePokemon.route) { MeinePokemon(navController) }
        composable(Screen.Teamerstellen.route) { TeamErstellenScreen(navController) }
        composable(Screen.Teambearbeiten.route) { TeamBearbeitenScreen(navController) }
        composable(Screen.ProfilScreen.route) { ProfilScreen(navController) }
        composable(Screen.Rucksack.route) { RucksackScreen(navController) }
        composable(Screen.Store.route) { StoreScreen(navController) }
        composable(Screen.Warenkorb.route) { WarenkorbScreen(navController) }
        composable(Screen.ProduktScreen.route) { ProduktScreen(navController) }
        composable(
            Screen.TeamSeite.route,
            arguments = listOf(
                navArgument("teamName"){
                    type = NavType.StringType
                }
            )
        ) {
            val teamName = remember {
                it.arguments?.getString("teamName")
            }
            TeamPage(navController = navController, teamName = teamName.toString())
        }
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
