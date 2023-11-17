package com.klimpel.abschlussarbeitmodul3.util

import androidx.compose.ui.graphics.Color
import com.klimpel.abschlussarbeitmodul3.ui.theme.LegendaryColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.EvoloutionColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.Grey
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.White

fun PokemonEvoloutionBorder(name: String) : List<Color> {
    return when (name) {
        "" -> listOf(Color.White, Color.White)
        "venusaur" -> EvoloutionColor
        "charizard" -> EvoloutionColor
        "blastoise" -> EvoloutionColor
        "butterfree" -> EvoloutionColor
        "beedrill" -> EvoloutionColor
        "pidgeot" -> EvoloutionColor
        "raticate" -> EvoloutionColor
        "fearow" -> EvoloutionColor
        "arbok" -> EvoloutionColor
        "raichu" -> EvoloutionColor
        "sandslash" -> EvoloutionColor
        "nidoqueen" -> EvoloutionColor
        "nidoking" -> EvoloutionColor
        "clefable" -> EvoloutionColor
        "ninetales" -> EvoloutionColor
        "wigglytuff" -> EvoloutionColor
        "golbat" -> EvoloutionColor
        "vileplume" -> EvoloutionColor
        "parasect" -> EvoloutionColor
        "venomoth" -> EvoloutionColor
        "dugtrio" -> EvoloutionColor
        "persian" -> EvoloutionColor
        "golduck" -> EvoloutionColor
        "primeape" -> EvoloutionColor
        "arcanine" -> EvoloutionColor
        "poliwrath" -> EvoloutionColor
        "alakazam" -> EvoloutionColor
        "machamp" -> EvoloutionColor
        "victreebel" -> EvoloutionColor
        "tentacruel" -> EvoloutionColor
        "golem" -> EvoloutionColor
        "rapidash" -> EvoloutionColor
        "slowbro" -> EvoloutionColor
        "magneton" -> EvoloutionColor
        "dodrio" -> EvoloutionColor
        "dewgong" -> EvoloutionColor
        "muk" -> EvoloutionColor
        "cloyster" -> EvoloutionColor
        "gengar" -> EvoloutionColor
        "hypno" -> EvoloutionColor
        "kingler" -> EvoloutionColor
        "electrode" -> EvoloutionColor
        "exeggutor" -> EvoloutionColor
        "marowak" -> EvoloutionColor
        "hitmonchan" -> EvoloutionColor
        "weezing" -> EvoloutionColor
        "rhydon" -> EvoloutionColor
        "chansey" -> EvoloutionColor
        "tangela" -> EvoloutionColor
        "kangaskhan" -> EvoloutionColor
        "seadra" -> EvoloutionColor
        "seaking" -> EvoloutionColor
        "starmie" -> EvoloutionColor
        "mr-mime" -> EvoloutionColor
        "scyther" -> EvoloutionColor
        "jynx" -> EvoloutionColor
        "electabuzz" -> EvoloutionColor
        "magmar" -> EvoloutionColor
        "pinsir" -> EvoloutionColor
        "tauros" -> EvoloutionColor
        "gyarados" -> EvoloutionColor
        "lapras" -> EvoloutionColor
        "ditto" -> EvoloutionColor
        "vaporeon" -> EvoloutionColor
        "onix" -> EvoloutionColor
        "jolteon" -> EvoloutionColor
        "flareon" -> EvoloutionColor
        "omastar" -> EvoloutionColor
        "kabutops" -> EvoloutionColor
        "aerodactyl" -> EvoloutionColor
        "snorlax" -> EvoloutionColor
        "lickitung" -> EvoloutionColor
        "articuno" -> LegendaryColor // Legendär
        "zapdos" -> LegendaryColor // Legendär
        "moltres" -> LegendaryColor // Legendär
        "dragonite" -> EvoloutionColor
        "mewtwo" -> LegendaryColor // Legendär
        "mew" -> LegendaryColor // Legendär
        else -> {
            listOf(LightBlue, LightBlue)
        }
    }
}

fun PokemonEvoloutionBorderTeamSeite(name: String) : List<Color> {
    return when (name) {
        "" -> listOf(Color.White, Color.White)
        "venusaur" -> EvoloutionColor
        "charizard" -> EvoloutionColor
        "blastoise" -> EvoloutionColor
        "butterfree" -> EvoloutionColor
        "beedrill" -> EvoloutionColor
        "pidgeot" -> EvoloutionColor
        "raticate" -> EvoloutionColor
        "fearow" -> EvoloutionColor
        "arbok" -> EvoloutionColor
        "raichu" -> EvoloutionColor
        "sandslash" -> EvoloutionColor
        "nidoqueen" -> EvoloutionColor
        "nidoking" -> EvoloutionColor
        "clefable" -> EvoloutionColor
        "ninetales" -> EvoloutionColor
        "wigglytuff" -> EvoloutionColor
        "golbat" -> EvoloutionColor
        "vileplume" -> EvoloutionColor
        "parasect" -> EvoloutionColor
        "venomoth" -> EvoloutionColor
        "dugtrio" -> EvoloutionColor
        "persian" -> EvoloutionColor
        "golduck" -> EvoloutionColor
        "primeape" -> EvoloutionColor
        "arcanine" -> EvoloutionColor
        "poliwrath" -> EvoloutionColor
        "alakazam" -> EvoloutionColor
        "machamp" -> EvoloutionColor
        "victreebel" -> EvoloutionColor
        "tentacruel" -> EvoloutionColor
        "golem" -> EvoloutionColor
        "rapidash" -> EvoloutionColor
        "slowbro" -> EvoloutionColor
        "magneton" -> EvoloutionColor
        "dodrio" -> EvoloutionColor
        "dewgong" -> EvoloutionColor
        "muk" -> EvoloutionColor
        "cloyster" -> EvoloutionColor
        "gengar" -> EvoloutionColor
        "hypno" -> EvoloutionColor
        "kingler" -> EvoloutionColor
        "electrode" -> EvoloutionColor
        "exeggutor" -> EvoloutionColor
        "marowak" -> EvoloutionColor
        "hitmonchan" -> EvoloutionColor
        "weezing" -> EvoloutionColor
        "rhydon" -> EvoloutionColor
        "chansey" -> EvoloutionColor
        "tangela" -> EvoloutionColor
        "kangaskhan" -> EvoloutionColor
        "seadra" -> EvoloutionColor
        "seaking" -> EvoloutionColor
        "starmie" -> EvoloutionColor
        "mr-mime" -> EvoloutionColor
        "scyther" -> EvoloutionColor
        "jynx" -> EvoloutionColor
        "electabuzz" -> EvoloutionColor
        "magmar" -> EvoloutionColor
        "pinsir" -> EvoloutionColor
        "tauros" -> EvoloutionColor
        "gyarados" -> EvoloutionColor
        "lapras" -> EvoloutionColor
        "ditto" -> EvoloutionColor
        "vaporeon" -> EvoloutionColor
        "onix" -> EvoloutionColor
        "jolteon" -> EvoloutionColor
        "flareon" -> EvoloutionColor
        "omastar" -> EvoloutionColor
        "kabutops" -> EvoloutionColor
        "aerodactyl" -> EvoloutionColor
        "snorlax" -> EvoloutionColor
        "lickitung" -> EvoloutionColor
        "articuno" -> LegendaryColor // Legendär
        "zapdos" -> LegendaryColor // Legendär
        "moltres" -> LegendaryColor // Legendär
        "dragonite" -> EvoloutionColor
        "mewtwo" -> LegendaryColor // Legendär
        "mew" -> LegendaryColor // Legendär
        else -> {
            listOf(White, White)
        }
    }
}