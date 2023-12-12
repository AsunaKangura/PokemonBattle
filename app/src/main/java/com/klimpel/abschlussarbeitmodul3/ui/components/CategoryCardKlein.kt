package com.klimpel.abschlussarbeitmodul3.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun CategoryCardKlein(
    onClick: () -> Unit,
    text: Int,
    image: Int,
    cardheight: Dp = 100.dp,
    cardpadding: Dp = 0.dp,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .height(cardheight)
            .padding(horizontal = cardpadding),
        shape = RoundedCornerShape(
            topStart = 25.dp,
            topEnd = 10.dp,
            bottomStart = 10.dp,
            bottomEnd = 25.dp
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(image),
                        contentScale = ContentScale.Crop,
                    )
            ) {
                TitelCardKlein(titel = text)
            }
        }
    }
}