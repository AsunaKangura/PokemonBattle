package com.klimpel.abschlussarbeitmodul3

/**
 * sealed class Screen(val route : String) - Diese Zeile definiert eine abstrakte Klasse "Screen" mit einem Konstruktorparameter "route", der den Namen des Bildschirms enthält.
 */
sealed class Screen(val route: String) {
    // Der Bildschirm für den Splash-Bildschirm
    object SplashOut : Screen("SplashOut")

    // Der Bildschirm für den Willkommensbildschirm
    object WelcomeScreen : Screen("WelcomeScreen")

    // Der Bildschirm für den Anmeldebildschirm
    object LoginScreen : Screen("LoginScreen")

    // Der Bildschirm für den Registrierungsbildschirm
    object Register : Screen("Register")

    // Der Bildschirm für den Startbildschirm
    object HomeScreen : Screen("HomeScreen")

    // Der Bildschirm für den Pokedex-Bildschirm
    object Pokedex : Screen("Pokedex")
    object PokemonDetailScreen : Screen("PokemonDetailScreen/{pokemonName}")

    object MeinePokemon : Screen("meinepokemon")

    object Teamubersicht : Screen("Teamübersicht")

    object Teamerstellen: Screen("Teamerstellen")

    object Teambearbeiten: Screen("Teambearbeiten")
}
