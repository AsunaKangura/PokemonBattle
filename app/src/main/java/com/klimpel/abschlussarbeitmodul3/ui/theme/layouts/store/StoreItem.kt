package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.store

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.components.Pokeball
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreItem(
    navController: NavController,
) {

    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

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
                .shadow(10.dp, RoundedCornerShape(10.dp))
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
                    .shadow(10.dp, RoundedCornerShape(10.dp))
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(LightBlueBackground)
                    .clickable { isSheetOpen = true }
            ) {
                Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "", tint = LightBlue, modifier = Modifier.size(14.dp))
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(calcDp(percentage = 0.10f, dimension = Dimension.Width))
                    .height(30.dp)
                    .shadow(10.dp, RoundedCornerShape(10.dp))
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(LightBlue)
                    .clickable { navController.navigate(Screen.ProduktScreen.route) }
            ) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = "", tint = Color.White, modifier = Modifier.size(14.dp))
            }
        }

    }

    if (isSheetOpen){
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { isSheetOpen = false },
            tonalElevation = 10.dp,
        ) {
            Divider(thickness = 2.dp, color = LightBlue)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.30f)
                    .background(Color.White)
            ) {
                Row (
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f)
                        .background(Color.White)
                ){
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight()
                    ) {
                        Text(text = "ItemName", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = LightBlue)
                    }
                }
                Divider(thickness = 2.dp, color = LightBlue)
                Row (
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.5f)
                        ) {
                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(containerColor = LightBlueBackground),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 10.dp
                                )
                            ) {
                                Icon(imageVector = Icons.Outlined.Remove, contentDescription = "", tint = LightBlue)
                            }

                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .width(80.dp)
                                    .fillMaxHeight(0.6f)
                                    .border(2.dp, color = LightBlue)
                            ){
                                Text(text = "01", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = LightBlue)
                            }

                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 10.dp
                                )
                            ) {
                                Icon(imageVector = Icons.Outlined.Add, contentDescription = "")
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 10.dp
                                )
                            ) {
                                Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "")
                                Spacer(modifier = Modifier.width(20.dp))
                                Text(text = "zum Warenkorb hinzufügen")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StoreItemProduktseite() {

    Column(
        modifier = Modifier
            .width(70.dp)
            .height(70.dp)
            .background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
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
    }
}
