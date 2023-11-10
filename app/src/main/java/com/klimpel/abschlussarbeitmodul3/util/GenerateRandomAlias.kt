package com.klimpel.abschlussarbeitmodul3.util

// Function to generate a random Pokemon-themed username
fun generatePokemonAlias(): String {
    // List of Pokemon names
    val pokemon = listOf("Pikachu", "Charizard", "Bulbasaur", "Squirtle", "Jigglypuff")
    // Get random index between 0 and list size
    val index = (0 until pokemon.size).random()
    // Get random number between 1 and 99
    val num = (1..99).random()
    // Combine Pokemon name and number
    return pokemon[index] + num
}