package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.store

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.ui.components.Pokeball
import com.klimpel.abschlussarbeitmodul3.ui.components.swipeableelements.SwipeableCardBoth
import com.klimpel.abschlussarbeitmodul3.ui.components.swipeableelements.SwipeableCardBothStore
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeFire
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp


@Composable
fun StoreItem(navController: NavController) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .height(160.dp)
            .background(Color.Transparent)
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f)
                .border(1.dp, LightBlue, shape = RoundedCornerShape(10.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(10.dp))
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f)
                        .background(LightBlueBackground)
                ) {
                    Pokeball()
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(LightBlue)
                ) {
                    Text(text = "Itemname", color = Color.White, fontSize = 12.sp)
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(calcDp(percentage = 0.10f, dimension = Dimension.Width))
                    .height(30.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(TypeFire)
            ) {
                Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "", tint = Color.White)
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(calcDp(percentage = 0.10f, dimension = Dimension.Width))
                    .height(30.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(LightBlue)
            ) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = "", tint = Color.White)
            }
        }

    }
}

@Preview
@Composable
fun previewStoreItem(){
    val context = LocalContext.current
    StoreItem(navController = NavController(context))
}