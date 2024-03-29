package com.klimpel.abschlussarbeitmodul3

sealed class Screen(val route: String) {
    object SplashOut : Screen("SplashOut")
    object WelcomeScreen : Screen("WelcomeScreen")
    object LoginScreen : Screen("LoginScreen")
    object Register : Screen("Register")
    object HomeScreen : Screen("HomeScreen")
    object Pokedex : Screen("Pokedex")
    object PokemonDetailScreen : Screen("PokemonDetailScreen/{pokemonName}")
    object MeinePokemon : Screen("meinepokemon")
    object Teamerstellen : Screen("Teamerstellen")
    object ProfilScreen : Screen("Profil")
    object TeamSeite : Screen("TeamSeite/{teamName}")
    object Rucksack : Screen("Rucksack")
    object Store : Screen("Store")
    object Warenkorb : Screen("Warenkorb")
    object ProduktScreen : Screen("ProduktScreen")
}
