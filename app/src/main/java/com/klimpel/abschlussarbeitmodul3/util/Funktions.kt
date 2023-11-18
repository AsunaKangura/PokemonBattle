package com.klimpel.abschlussarbeitmodul3.util

import androidx.compose.ui.graphics.Color
import com.asunakangura.pokemonbattle.data.remote.responses.Type
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.Green
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeFire

// Wird für die Ermittlung der Winrate Farben verwendet
fun winratecolor(winrate: Int): Color {
    val color = if (winrate <= 25){
        DeepRed
    }else if (winrate < 55){
        TypeFire
    }else{
        Green
    }
    return color
}

fun backgroundBrush(typelist: List<Type>): List<Color> {
    val colorList: MutableList<Color> = mutableListOf()
    return if (typelist.isNotEmpty()) {
        if (typelist.size > 1) {
            colorList.add(parseTypeToColor(typelist.first()))
            colorList.add(parseTypeToColor(typelist.last()))
        } else {
            colorList.add(parseTypeToColor(typelist.first()))
            colorList.add(parseTypeToColor(typelist.first()))
        }
        colorList
    } else {
        listOf(LightBlue, LightBlue)
    }
}