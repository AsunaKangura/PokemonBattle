package com.klimpel.abschlussarbeitmodul3.util

import androidx.compose.ui.graphics.Color
import com.asunakangura.pokemonbattle.data.remote.responses.Stat
import com.asunakangura.pokemonbattle.data.remote.responses.Type
import com.klimpel.abschlussarbeitmodul3.ui.theme.AtkColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.DefColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.HPColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.SpAtkColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.SpDefColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.SpdColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeBug
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeDark
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeDragon
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeElectric
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeFairy
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeFighting
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeFire
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeFlying
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeGhost
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeGrass
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeGround
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeIce
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeNormal
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypePoison
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypePsychic
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeRock
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeSteel
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeWater
import java.util.Locale


fun parseTypeToColor(type: Type): Color {
    return when(type.type.name.lowercase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when(stat.stat.name.toLowerCase()) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}