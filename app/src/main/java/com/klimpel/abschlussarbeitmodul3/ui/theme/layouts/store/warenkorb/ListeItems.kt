package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.store.warenkorb

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue

@Composable
fun ItemListe(){
    // Lister der Items
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .padding(top = 20.dp)
            .padding(horizontal = 20.dp)
            .shadow(10.dp, shape = RoundedCornerShape(20.dp))
            .clip(shape = RoundedCornerShape(20.dp))
            .border(2.dp, color = LightBlue, shape = RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        TableHeader()
        Column(
            modifier = Modifier
                .fillMaxWidth(0.90f)
                .fillMaxHeight()
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                val itemcount = 5

                item { Spacer(modifier = Modifier.height(5.dp)) }
                items(itemcount) {
                    TableRow()
                }
                item { Spacer(modifier = Modifier.height(5.dp)) }
            }
        }
    }
}