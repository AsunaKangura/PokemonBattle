package com.klimpel.abschlussarbeitmodul3.util


/**
 * Generates a random Pokemon alias by combining a Pokemon name with a random number.
 *
 * @return The generated Pokemon alias.
 */
fun generatePokemonAlias(): String {
    // List of Pokemon names
    val pokemon = listOf("Pikachu", "Charizard", "Bulbasaur", "Squirtle", "Jigglypuff")

    // Generate a random index within the range of the Pokemon list
    val index = (0 until pokemon.size).random()

    // Generate a random number between 1 and 99
    val num = (1..99).random()

    // Combine the Pokemon name with the random number to create the alias
    return pokemon[index] + num
}