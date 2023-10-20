package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profil

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.ProfilViewModel
import com.klimpel.pokemonbattlefinal.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilbearbeitenScreen(
    navController: NavController,
    topPadding: Dp = 50.dp,
    profilImageSize: Dp = 120.dp,
    viewModel: ProfilViewModel = hiltViewModel()
) {

    val setAvatar = viewModel.findAvatar()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.2f)
                .background(LightBlueBackground)
                .padding(bottom = 16.dp)
        ) {

            ProfilTopSection(
                navController = navController,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBlueBackground)
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {

                val (profilImage, contentcard) = createRefs()

                Card(
                    modifier = Modifier
                        .width(calcDp(percentage = 0.8f, dimension = Dimension.Width))
                        .height(calcDp(percentage = 0.75f, dimension = Dimension.Height))
                        .padding(top = 30.dp)
                        .constrainAs(contentcard) {
                            centerHorizontallyTo(parent)
                        },
                    shape = RoundedCornerShape(
                        topStart = 50.dp,
                        topEnd = 20.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 50.dp
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),
                ) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val (titel, storeglobaldex, content) = createRefs()
                        // Profil Titel
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,
                            ),
                            border = BorderStroke(
                                4.dp, DeepRed
                            ),
                            modifier = Modifier
                                .width(200.dp)
                                .height(60.dp)
                                .constrainAs(titel) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                },
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
                                    text = stringResource(id = R.string.titelprofilbearbeiten),
                                    color = DeepRed,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }

                        // Reihe für die Store und PokeDex
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(calcDp(percentage = 0.15f, dimension = Dimension.Height))
                                .constrainAs(storeglobaldex) {
                                    top.linkTo(titel.bottom, 20.dp)
                                    centerHorizontallyTo(parent)
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            // Pokemon Store
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .fillMaxHeight()
                            ) {
                                ConstraintLayout(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    val (text, image) = createRefs()

                                    Card(
                                        onClick = {  },
                                        modifier = Modifier
                                            .width(130.dp)
                                            .height(70.dp)
                                            .constrainAs(image) {
                                                centerHorizontallyTo(parent)
                                                top.linkTo(parent.top, 10.dp)
                                            },
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
                                        ConstraintLayout(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .paint(
                                                    painterResource(R.drawable.pokemon_centre_launch_edited_large),
                                                    contentScale = ContentScale.Crop,
                                                ),
                                        ) {}
                                    }

                                    Text(
                                        text = stringResource(id = R.string.pokestorecard),
                                        modifier = Modifier
                                            .constrainAs(text) {
                                                bottom.linkTo(parent.bottom)
                                                centerHorizontallyTo(parent)
                                            },
                                        fontWeight = FontWeight.Bold,
                                        color = DeepRed
                                    )
                                }
                            }
                            // Pokemon Battle
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                ConstraintLayout(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    val (text, image) = createRefs()

                                    Card(
                                        onClick = { navController.navigate(Screen.Pokedex.route) },
                                        modifier = Modifier
                                            .width(130.dp)
                                            .height(70.dp)
                                            .constrainAs(image) {
                                                centerHorizontallyTo(parent)
                                                top.linkTo(parent.top, 10.dp)
                                            },
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
                                        ConstraintLayout(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .paint(
                                                    painterResource(R.drawable.pokemondex),
                                                    contentScale = ContentScale.Crop,
                                                ),
                                        ) {}
                                    }
                                    Text(
                                        text = stringResource(id = R.string.pokedexcard),
                                        modifier = Modifier
                                            .constrainAs(text) {
                                                bottom.linkTo(parent.bottom)
                                                centerHorizontallyTo(parent)
                                            },
                                        fontWeight = FontWeight.Bold,
                                        color = DeepRed
                                    )
                                }
                            }
                        }

                        Column(
                            modifier= Modifier
                                .fillMaxSize()
                                .constrainAs(content){
                                    top.linkTo(storeglobaldex.bottom, 30.dp)
                                }
                        ) {
                            var textStateUsername by remember { mutableStateOf(viewModel.currentUser?.let {
                                TextFieldValue(
                                    it.alias
                                )
                            }) }

                            textStateUsername?.let {
                                OutlinedTextField(
                                    value = it,
                                    onValueChange = { textStateUsername = it },
                                    label = {
                                        Text(
                                            "Alias",
                                            fontSize = 14.sp
                                        )
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        focusedBorderColor = DeepRed,
                                        unfocusedBorderColor = Color.Black,
                                        focusedLabelColor = DeepRed,
                                        unfocusedLabelColor = Color.Black,
                                        textColor = Color.Black,
                                    ),
                                    modifier = Modifier
                                        .padding(horizontal = 40.dp),
                                    enabled = false
                                )
                            }
                        }


                    }

                }
                // ProfilBild
                Card(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, DeepRed, CircleShape)
                        .constrainAs(profilImage) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end, 20.dp)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Column(

                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (setAvatar != null) {
                            Image(
                                painterResource(id = setAvatar.imageResource),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize(0.6f),
                            )
                        }
                    }
                }
            }
        }
    }

}