package com.klimpel.abschlussarbeitmodul3.util

import androidx.compose.ui.graphics.Color
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.Green
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