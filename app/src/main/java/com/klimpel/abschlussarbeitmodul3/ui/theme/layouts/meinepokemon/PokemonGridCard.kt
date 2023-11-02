package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.meinepokemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonGrindEntry
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.util.parsePokemonNameToGerman

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonGridCard(
    navController: NavController,
    pokemon: PokemonGrindEntry
) {

    AbschlussarbeitModul3Theme {

        Column(
            modifier = Modifier
               // .width(calcDp(percentage = 0.4f, dimension = Dimension.Width))
                //.height(calcDp(percentage = 0.2f, dimension = Dimension.Height))
            //.background(DeepRed)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                val (contentcard, amount) = createRefs()

                Card(
                    onClick = { navController.navigate("PokemonDetailScreen/${pokemon.name}") },
                    modifier = Modifier
                        .width(calcDp(percentage = 0.30f, dimension = Dimension.Width))
                        .height(calcDp(percentage = 0.15f, dimension = Dimension.Height))
                        .constrainAs(contentcard) {
                            centerHorizontallyTo(parent)
                            centerVerticallyTo(parent)
                        },
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 10.dp
                    ),
                    border = BorderStroke(2.dp, Color.White)

                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        val (name, level, image) = createRefs()

                        // Anzahl der Pokemons
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .fillMaxHeight(0.20f),
                            shape = RoundedCornerShape(
                                topStart = 25.dp,
                                topEnd = 10.dp,
                                bottomStart = 10.dp,
                                bottomEnd = 25.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            border = BorderStroke(1.dp, Color.White)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Text(
                                    text = "Anzahl: ${pokemon.anzahl}",
                                    color = Color.Black,
                                    fontSize = 10.sp
                                )
                            }
                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .fillMaxHeight(0.6f)
                                .constrainAs(image) {
                                    centerHorizontallyTo(parent)
                                    top.linkTo(parent.top, 25.dp)
                                }
                        ) {
                            AsyncImage(
                                model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png",
                                contentDescription = "",
                                modifier = Modifier.size(120.dp)
                            )
                        }

                        // Name des Pokemon
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.25f)
                                .constrainAs(amount) {
                                    bottom.linkTo(parent.bottom)
                                }
                                .background(LightBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = parsePokemonNameToGerman(pokemon.name),
                                color = Color.White,
                                fontSize = 16.sp,
                            )
                        }
                    }
                }

                // Level Pokemon
                Card(
                    modifier = Modifier
                        .constrainAs(amount) {
                            top.linkTo(parent.top, 10.dp)
                            end.linkTo(parent.end, 10.dp)
                        }
                        .size(40.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = LightBlue
                    ),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(2.dp, Color.White)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = pokemon.level.toString(),
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}