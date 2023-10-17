package com.klimpel.abschlussarbeitmodul3

/**
 * sealed class Screen(val route : String) - Diese Zeile definiert eine abstrakte Klasse "Screen" mit einem Konstruktorparameter "route", der den Namen des Bildschirms enthält.
 */
sealed class Screen(val route: String) {
    // Der Bildschirm für den Splash-Bildschirm
    object SplashOut : Screen("SplashScreen")

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

    // Der Bildschirm für den Pokemon-Detailbildschirm
    // Dieser Bildschirm hat zusätzliche Parameter für die dominierende Farbe und den Pokemon-Namen
    object PokemonDetailScreen : Screen("PokemonDetailScreen/{pokemonName}")
}
