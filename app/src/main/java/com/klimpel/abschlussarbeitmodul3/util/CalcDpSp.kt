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
 * Diese Funktion erlaubt es mir meine  SP die ich verwende Anhand der Displaygröße auszurechnen. Das heist die Schriftgröße auf dem Screen kann ich damit prozentual anlegen. Somit ändert sich die Darstellung nicht wenn sich die Displaygröße ändert.
 */
@Composable
fun calcSp(percentage: Float): TextUnit {
    val dpi = LocalContext.current.resources.displayMetrics.densityDpi.toFloat()
    return with(LocalDensity.current) {
        (percentage * density * dpi).toSp()
    }
}
/**
 * Diese Funktion erlaubt es mir meine  DP die ich verwende Anhand der Displaygröße auszurechnen. Das heist die Elemente und Abstände auf dem Screen kann ich damit prozentual anlegen. Somit ändert sich die Darstellung nicht wenn sich die Displaygröße ändert.
 */
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