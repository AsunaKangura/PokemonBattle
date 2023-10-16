package com.klimpel.abschlussarbeitmodul3.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

enum class Dimension {
    Width,
    Height,
}

/**
 * Diese beiden Funktionen erlauben es mir meine DP und SP die ich ver wende anhand der Displaygröße auszurechnen. Das heist die Elemente auf dem Screen so wie Schriften kann ich damit Prozentual anlegen. Somit ändert sich die Darstellung nicht wenn sich die Displaygröße ändert.
 */
@Composable
fun calcSp(percentage: Float): TextUnit {
    val dpi = LocalContext.current.resources.displayMetrics.densityDpi.toFloat()
    return with(LocalDensity.current) {
        (percentage * density * dpi).toSp()
    }
}

@Composable
fun calcDp(percentage: Float, dimension: Dimension): Dp {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp
    return if (dimension == Dimension.Width) {
        with(LocalDensity.current) {
            (percentage * density * screenWidth).toDp()
        }
    } else {
        with(LocalDensity.current) {
            (percentage * density * screenHeight).toDp()
        }
    }
}