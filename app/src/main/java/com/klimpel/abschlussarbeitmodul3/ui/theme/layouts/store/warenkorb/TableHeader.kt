package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.store.warenkorb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp

@Composable
fun TableHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(calcDp(percentage = 0.9f, dimension = Dimension.Width))
            .height(calcDp(percentage = 0.05f, dimension = Dimension.Height))
            .background(LightBlue)
        //.background(DeepRed)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight()
            //.background(DeepRed)
        ) {
            val lfdWeight = .08f // 5%
            val ItemWeight = .16f // 30%
            val anzahlWeight = .11f // 20%
            val preisWeight = .11f // 30%
            val deleteWeight = .11f // 10%

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightBlue)
            ) {
                TableCellHeader(text = "Lfd.", weight = lfdWeight)
                TableCellHeader(text = "Itemname", weight = ItemWeight)
                TableCellHeader(text = "Anzahl", weight = anzahlWeight)
                TableCellHeader(text = "Preis", weight = preisWeight)
                TableCellHeader(text = "Aktion", weight = deleteWeight)
            }
        }
    }
}