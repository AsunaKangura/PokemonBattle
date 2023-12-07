package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.store.warenkorb

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp


@Preview
@Composable
fun WarenkorbItem() {

    Column(
        modifier = Modifier
            .width(calcDp(percentage = 0.9f, dimension = Dimension.Width))
            .height(calcDp(percentage = 0.05f, dimension = Dimension.Height))
        //.background(DeepRed)
    ) {
        val lfdWeight = .1f // 5%
        val ItemWeight = .18f // 30%
        val anzahlWeight = .1f // 20%
        val preisWeight = .1f // 30%
        val deleteWeight = .1f // 10%

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

@Composable
fun RowScope.TableCellHeader(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, LightBlue)
            .weight(weight)
            .padding(12.dp),
        fontSize = 12.sp,
        color = Color.White
    )
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            //.border(1.dp, color = LightBlue)
            .weight(weight)
            .padding(12.dp),
        fontSize = 12.sp,
        color = LightBlue,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TableCellDeleteIcon(
    itemname: String
) {
    val openDeleteDialog = remember { mutableStateOf(false) }
    if (openDeleteDialog.value){
        AlertDialog(
            onDismissRequest = {
                openDeleteDialog.value = false
            },
            title = {
                Text(text = "Item entfernen")
            },
            text = {
                Text(text = "Sind Sie sicher das sie Itemname löschen wollen?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDeleteDialog.value = false
                    }
                ) {
                    Text("Löschen", color = DeepRed, fontWeight = FontWeight.Bold)
                }
            },
            tonalElevation = AlertDialogDefaults.TonalElevation,
            dismissButton = {
                TextButton(
                    onClick = {
                        openDeleteDialog.value = false
                    }
                ) {
                    Text("Abbrechen")
                }
            },
            shape = RoundedCornerShape(
                topStart = 50.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 50.dp
            ),
            modifier = Modifier
                .border(
                    4.dp,
                    DeepRed,
                    shape = RoundedCornerShape(
                        topStart = 50.dp,
                        topEnd = 20.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 50.dp
                    )
                )
        )
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(0.15f).fillMaxHeight()
    ){
        Icon(
            imageVector = Icons.Outlined.Delete,
            contentDescription = "",
            modifier = Modifier
                .clickable { openDeleteDialog.value = true}
                .size(26.dp),
            tint = DeepRed

        )
    }
}