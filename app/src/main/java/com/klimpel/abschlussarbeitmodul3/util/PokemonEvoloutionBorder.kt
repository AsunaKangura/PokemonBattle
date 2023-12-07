package com.klimpel.abschlussarbeitmodul3.util

import androidx.compose.ui.graphics.Color
import com.klimpel.abschlussarbeitmodul3.ui.theme.LegendaryColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.EvoloutionColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue

/**
 * Returns a list of colors for the Pokemon evolution border.
 *
 * @param name The name of the Pokemon.
 * @return The list of colors.
 */
fun pokemonEvoloutionBorder(name: String): List<Color> {
    return when (name.lowercase()) {
        "" -> listOf(LightBlue, LightBlue)
        // Pokemon that evolve
        "venusaur", "charizard", "blastoise", "butterfree", "beedrill", "pidgeot", "raticate", "fearow", "arbok", "raichu", "sandslash", "nidoqueen", "nidoking", "clefable", "ninetales", "wigglytuff", "golbat", "vileplume", "parasect", "venomoth", "dugtrio", "persian", "golduck", "primeape", "arcanine", "poliwrath", "alakazam", "machamp", "victreebel", "tentacruel", "golem", "rapidash", "slowbro", "magneton", "dodrio", "dewgong", "muk", "cloyster", "gengar", "hypno", "kingler", "electrode", "exeggutor", "marowak", "hitmonchan", "weezing", "rhydon", "chansey", "tangela", "kangaskhan", "seadra", "seaking", "starmie", "mr-mime", "scyther", "jynx", "electabuzz", "magmar", "pinsir", "tauros", "gyarados", "lapras", "ditto", "vaporeon", "onix", "jolteon", "flareon", "omastar", "kabutops", "aerodactyl", "snorlax", "lickitung",
        "dragonite" -> EvoloutionColor
        // Legendary Pokemon
        "articuno", "zapdos", "moltres", "mewtwo", "mew" -> LegendaryColor
        else -> listOf(LightBlue, LightBlue)
    }
}