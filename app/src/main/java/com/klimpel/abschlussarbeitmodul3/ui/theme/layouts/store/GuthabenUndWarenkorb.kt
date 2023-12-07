package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.store

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuthabenUndWarenkorb(navController: NavController){

    // Guthaben und Warenkoprb
    Divider(thickness = 1.dp, color = LightBlue)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(calcDp(percentage = 0.08f, dimension = Dimension.Height))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
                .padding(horizontal = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = "PokeDollar:",
                    color = LightBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "1.000.000 $",
                    color = DeepRed,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(5.dp)
            ) {
                ExtendedFloatingActionButton(
                    onClick = { navController.navigate(Screen.Warenkorb.route)},
                    containerColor = LightBlueBackground,
                    contentColor = Color.White,
                ) {
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = LightBlue
                            ) {
                                Text(text = "10", fontSize = 12.sp)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingCart,
                            contentDescription = "",
                            tint = LightBlue
                        )
                    }
                }
            }
        }
    }
    Divider(thickness = 1.dp, color = LightBlue)

}