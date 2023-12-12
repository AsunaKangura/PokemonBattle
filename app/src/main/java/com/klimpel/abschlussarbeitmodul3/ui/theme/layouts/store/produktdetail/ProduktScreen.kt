package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.store.produktdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.ui.components.Pokeball
import com.klimpel.abschlussarbeitmodul3.ui.components.TopAppBarTitelBackArrowProduktScreen
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.seanproctor.datatable.Table
import com.seanproctor.datatable.TableColumnDefinition



@Composable
fun ProduktScreen(navController: NavController) {

    Scaffold(
        topBar = { TopAppBarTitelBackArrowProduktScreen("Item Name", navController) },
        containerColor = LightBlueBackground,
    ) { innerpadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {
            Divider(thickness = 4.dp, color = LightBlue)
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(calcDp(percentage = 0.30f, dimension = Dimension.Height))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.6f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight()
                            .padding(10.dp)
                            .shadow(10.dp, shape = RoundedCornerShape(20.dp))
                            .clip(shape = RoundedCornerShape(20.dp))
                            .background(Color.White)
                            .border(1.dp, color = LightBlue, shape = RoundedCornerShape(20.dp))
                            .padding(5.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
                        ) {
                            Pokeball()
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(10.dp)
                    ) {
                        Text(
                            text = """Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et""",
                            color = LightBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                //Divider(thickness = 1.dp, color = LightBlue)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                    //.background(Color.White)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.4f)
                        ) {
                            Button(
                                onClick = {  },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 10.dp
                                ),
                                //modifier = Modifier.height(40.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Remove,
                                    contentDescription = "",
                                    tint = LightBlue,
                                )
                            }

                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(40.dp)
                                    .clip(shape = RoundedCornerShape(20.dp))
                                    .border(
                                        2.dp, color = LightBlue, shape = RoundedCornerShape(20.dp)
                                    )
                                    .background(Color.White)
                            ) {
                                Text(
                                    text = "01",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = LightBlue
                                )
                            }

                            Button(
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 10.dp
                                ),
                                // modifier = Modifier.height(40.dp)
                            ) {
                                Icon(imageVector = Icons.Outlined.Add, contentDescription = "")
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                        ) {
                            Button(
                                onClick = {  },
                                colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 10.dp
                                ),
                                modifier = Modifier.height(30.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.ShoppingCart,
                                    contentDescription = ""
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                Text(text = "zum Warenkorb hinzufügen", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            // Preis Liste
            Divider(thickness = 1.dp, color = LightBlue)
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(calcDp(percentage = 0.20f, dimension = Dimension.Height))
                    .background(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight()
                    ) {
                        Table(
                            modifier = Modifier.fillMaxWidth(),
                            columns = listOf(
                                TableColumnDefinition(
                                    alignment = Alignment.Center,
                                ) {
                                    Text(text = "Anzahl", color = LightBlue, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                }, TableColumnDefinition(
                                    alignment = Alignment.Center,
                                ) {
                                    Text(text = "Preis", color = LightBlue, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                }
                            ),
                        ) {
                            row {
                                cell { Text(text = "1 - 4") }
                                cell { Text(text = "100 $") }
                            }
                            row {
                                cell { Text(text = "5 - 9") }
                                cell { Text(text = "95 $") }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Table(
                            modifier = Modifier.fillMaxWidth(),
                            columns = listOf(
                                TableColumnDefinition(
                                    alignment = Alignment.Center,
                                ) {
                                    Text(text = "Anzahl", color = LightBlue, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                }, TableColumnDefinition(
                                    alignment = Alignment.Center,
                                ) {
                                    Text(text = "Preis", color = LightBlue, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                }
                            ),
                        ) {
                            row {
                                cell { Text(text = "10 - 19") }
                                cell { Text(text = "90 $") }
                            }
                            row {
                                cell { Text(text = "20+") }
                                cell { Text(text = "85 $") }
                            }
                        }
                    }
                }
            }
            Divider(thickness = 1.dp, color = LightBlue)
            Spacer(modifier = Modifier.height(20.dp))
            Divider(thickness = 1.dp, color = LightBlue)
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(calcDp(percentage = 0.12f, dimension = Dimension.Height))
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight()
                        .padding(top = 5.dp)
                ) {
                    Text(text = "Top Gewinne", color = LightBlue, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier.padding(top = 10.dp),
                    ){
                        items(10){
                            Pokeball()
                        }
                    }
                }
            }
            Divider(thickness = 1.dp, color = LightBlue)
            Spacer(modifier = Modifier.height(20.dp))
            Divider(thickness = 1.dp, color = LightBlue)
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(calcDp(percentage = 0.18f, dimension = Dimension.Height))
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight()
                        .padding(top = 5.dp)
                ) {
                    Text(text = "Normale Gewinne", color = LightBlue, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    LazyHorizontalGrid(
                        rows = GridCells.Adaptive(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.9f),
                        verticalArrangement = Arrangement.spacedBy(0.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        items(20){
                            Image(imageVector = Icons.Outlined.Person, contentDescription = "", modifier = Modifier.size(50.dp))
                        }
                    }
                }
            }
            Divider(thickness = 1.dp, color = LightBlue)
        }
    }
}

@Preview
@Composable
fun PreviewProduktScreen() {
    val context = LocalContext.current
    ProduktScreen(navController = NavController(context))
}