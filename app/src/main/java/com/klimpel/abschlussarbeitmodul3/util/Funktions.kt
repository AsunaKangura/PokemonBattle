package com.klimpel.abschlussarbeitmodul3.util

import androidx.compose.ui.graphics.Color
import com.asunakangura.pokemonbattle.data.remote.responses.Type
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.Green
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeFire


/**
 * Returns the color based on the winrate.
 *
 * @param winrate The winrate value.
 * @return The corresponding color.
 */
fun winratecolor(winrate: Int): Color {
    val color = if (winrate <= 25) {
        DeepRed
    } else if (winrate < 55) {
        TypeFire
    } else {
        Green
    }
    return color
}

/**
 * Returns a list of colors for the given type list.
 *
 * @param typelist The list of types.
 * @return The list of colors.
 */
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

/**
 * Returns a list of colors for the given type list as strings.
 *
 * @param typelist The list of type strings.
 * @return The list of colors.
 */
fun backgroundBrushListString(typelist: List<String>): List<Color> {
    val colorList: MutableList<Color> = mutableListOf()
    return if (typelist.isNotEmpty()) {
        if (typelist.size > 1) {
            colorList.add(parseTypeToColorString(typelist.first()))
            colorList.add(parseTypeToColorString(typelist.last()))
        } else {
            colorList.add(parseTypeToColorString(typelist.first()))
            colorList.add(parseTypeToColorString(typelist.first()))
        }
        colorList
    } else {
        listOf(LightBlue, LightBlue)
    }
}