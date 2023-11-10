package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.homescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily

@Composable
fun CategoryCard(
    onClick: () -> Unit,
    text: String,
    image: Int,
    cardheight: Dp = 200.dp,
    cardpadding: Dp = 20.dp,
    fontSize: TextUnit = 18.sp,
    titelcardwidth: Dp = 200.dp,
    titelcardheight: Dp = 50.dp,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .height(cardheight)
            .padding(horizontal = cardpadding),
        shape = RoundedCornerShape(
            topStart = 50.dp,
            topEnd = 20.dp,
            bottomStart = 20.dp,
            bottomEnd = 50.dp
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

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),
                    border = BorderStroke(
                        4.dp, LightBlue
                    ),
                    modifier = Modifier
                        .width(titelcardwidth)
                        .height(titelcardheight),
                    shape = RoundedCornerShape(
                        topStart = 50.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 50.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = text,
                            color = LightBlue,
                            fontSize = fontSize,
                            fontWeight = FontWeight.Bold,
                            fontFamily = pokemonFontFamily,
                            textAlign = TextAlign.Center,
                        )
                    }
                }

            }
        }

    }
}
