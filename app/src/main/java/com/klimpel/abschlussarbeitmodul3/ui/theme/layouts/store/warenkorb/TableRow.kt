package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.store.warenkorb

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp

@Composable
fun TableRow() {
    Column(
        modifier = Modifier
            .width(calcDp(percentage = 0.9f, dimension = Dimension.Width))
            .height(calcDp(percentage = 0.05f, dimension = Dimension.Height))
        //.background(DeepRed)
    ) {
        val lfdWeight = .1f // 5%
        val itemWeight = .18f // 30%
        val anzahlWeight = .1f // 20%
        val preisWeight = .1f // 30%

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .shadow(5.dp, shape = RoundedCornerShape(10.dp))
                .border(2.dp, color = LightBlue, shape = RoundedCornerShape(10.dp))
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            TableCell(text = "1", weight = lfdWeight)
            TableCell(text = "Standart Ball", weight = itemWeight)
            TableCell(text = "5", weight = anzahlWeight)
            TableCell(text = "500 $", weight = preisWeight)
            TableCellDeleteIcon()
        }
    }
}