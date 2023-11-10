package com.klimpel.abschlussarbeitmodul3.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily


@Composable
fun TitelCard(titel: Int){

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(
            4.dp, LightBlue
        ),
        modifier = Modifier
            .width(200.dp)
            .height(60.dp),
        shape = RoundedCornerShape(
            topStart = 50.dp,
            topEnd = 0.dp,
            bottomStart = 0.dp,
            bottomEnd = 50.dp
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = titel),
                color = LightBlue,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = pokemonFontFamily,
                textAlign = TextAlign.Center,
            )
        }
    }
}