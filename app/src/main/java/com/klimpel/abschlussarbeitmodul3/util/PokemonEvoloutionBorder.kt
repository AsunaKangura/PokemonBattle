package com.klimpel.abschlussarbeitmodul3.util

import androidx.compose.ui.graphics.Color
import com.klimpel.abschlussarbeitmodul3.ui.theme.RainbowColor

fun PokemonEvoloutionBorder(name: String) : List<Color> {
    return when (name) {
        "venusaur" -> RainbowColor
        "charizard" -> RainbowColor
        "blastoise" -> RainbowColor
        "butterfree" -> RainbowColor
        "beedrill" -> RainbowColor
        "pidgeot" -> RainbowColor
        "raticate" -> RainbowColor
        "fearow" -> RainbowColor
        "arbok" -> RainbowColor
        "raichu" -> RainbowColor
        "sandslash" -> RainbowColor
        "nidoqueen" -> RainbowColor
        "nidoking" -> RainbowColor
        "clefable" -> RainbowColor
        "ninetales" -> RainbowColor
        "wigglytuff" -> RainbowColor
        "golbat" -> RainbowColor
        "vileplume" -> RainbowColor
        "parasect" -> RainbowColor
        "venomoth" -> RainbowColor
        "dugtrio" -> RainbowColor
        "persian" -> RainbowColor
        "golduck" -> RainbowColor
        "primeape" -> RainbowColor
        "arcanine" -> RainbowColor
        "poliwrath" -> RainbowColor
        "alakazam" -> RainbowColor
        "machamp" -> RainbowColor
        "victreebel" -> RainbowColor
        "tentacruel" -> RainbowColor
        "golem" -> RainbowColor
        "rapidash" -> RainbowColor
        "slowbro" -> RainbowColor
        "magneton" -> RainbowColor
        "dodrio" -> RainbowColor
        "dewgong" -> RainbowColor
        "muk" -> RainbowColor
        "cloyster" -> RainbowColor
        "gengar" -> RainbowColor
        "hypno" -> RainbowColor
        "kingler" -> RainbowColor
        "electrode" -> RainbowColor
        "exeggutor" -> RainbowColor
        "marowak" -> RainbowColor
        "hitmonchan" -> RainbowColor
        "weezing" -> RainbowColor
        else -> {
            listOf(Color.White, Color.White)
        }
    }
}