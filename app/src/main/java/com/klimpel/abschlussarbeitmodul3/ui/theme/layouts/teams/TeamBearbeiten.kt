package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.components.CardWithAnimatedBorder
import com.klimpel.abschlussarbeitmodul3.ui.components.GradientButton
import com.klimpel.abschlussarbeitmodul3.ui.components.PokemonTeamCard
import com.klimpel.abschlussarbeitmodul3.ui.components.PokemonTeamCardAddBearbeiten

import com.klimpel.abschlussarbeitmodul3.ui.components.messageDialogError
import com.klimpel.abschlussarbeitmodul3.ui.components.swipeableelements.SwipeableCardBoth
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.topappbars.TopAppBarTitelBackArrowTeamBearbeiten
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.PokemonEvoloutionBorder
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.MeinePokemonViewModel
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel
import com.klimpel.pokemonbattlefinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamBearbeitenScreen(
    navController: NavController,
    viewModelTeam: TeamViewModel = hiltViewModel(),
    viewModel: MeinePokemonViewModel = hiltViewModel(),
) {

    val openInfoDialog = remember { mutableStateOf(false) }
    if (openInfoDialog.value) {
        Dialog(onDismissRequest = { openInfoDialog.value = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(calcDp(percentage = 0.8f, dimension = Dimension.Height)),
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 20.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 50.dp
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            ) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {

                    val (content) = createRefs()

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.9f)
                            .constrainAs(content) {
                                centerHorizontallyTo(parent)
                                centerVerticallyTo(parent)
                            }
                    ) {
                        LazyColumn(
                            contentPadding = PaddingValues(10.dp),
                        ) {
                            item { Spacer(modifier = Modifier.height(30.dp)) }
                            item {
                                Text(
                                    text = "Pokemon Auswahl",
                                    fontSize = 18.sp,
                                    color = LightBlue,
                                    fontWeight = FontWeight.Bold
                                )
                                Divider(
                                    thickness = 1.dp,
                                    color = LightBlue,
                                    modifier = Modifier.fillMaxWidth(0.8f)
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "Bei der Pokemon Auswahl ist folgendes zu beachten: \n- Nur Pokemon im Besitz kann man Auswählen\n- Es müssen alle 3 Pokemon Ausgewählt werden",
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                )
                            }
                            item { Spacer(modifier = Modifier.height(30.dp)) }
                            item {
                                Text(
                                    text = "Team speichern",
                                    fontSize = 18.sp,
                                    color = LightBlue,
                                    fontWeight = FontWeight.Bold
                                )
                                Divider(
                                    thickness = 1.dp,
                                    color = LightBlue,
                                    modifier = Modifier.fillMaxWidth(0.8f)
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "Beim Speicher muss ein Teamname vergeben sein. Alle Pokemonplätze müssen belegt sein.",
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                )
                            }
                            item { }
                        }
                    }
                }
            }
        }
    }

    var clickedID = 0
    val context = LocalContext.current

    val currentTeam by viewModelTeam.currentTeam.collectAsStateWithLifecycle()
    Log.e("LOG_CURRENT_TEAM", "$currentTeam")

    AbschlussarbeitModul3Theme {
        Scaffold(
            topBar = {
                TopAppBarTitelBackArrowTeamBearbeiten(
                    pageTitle = R.string.titelTeamBearbeitenScreen,
                    navController = navController
                )
            },
            containerColor = LightBlueBackground,
        ) { innerpadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerpadding)
            ) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val (dividertopappbar, teamname, teaminfo) = createRefs()

                    Divider(
                        thickness = 4.dp,
                        color = LightBlue,
                        modifier = Modifier.constrainAs(dividertopappbar) { top.linkTo(parent.top) }
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.2f)
                            .constrainAs(teamname) {
                                centerHorizontallyTo(parent)
                                top.linkTo(dividertopappbar.bottom)
                            }

                    ) {
                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            val (btninfo, inputteamname, teaminfotext) = createRefs()

                            Text(
                                text = currentTeam.teamName,
                                modifier = Modifier
                                    .constrainAs(inputteamname) {
                                        bottom.linkTo(parent.bottom)
                                        //centerHorizontallyTo(parent)
                                    },
                                fontSize = 28.sp,
                                color = LightBlue,
                                fontWeight = FontWeight.Bold,
                                fontFamily = pokemonFontFamily
                            )

                            IconButton(
                                // Onlick Open Dialog für die Informationen.
                                onClick = { openInfoDialog.value = true },
                                modifier = Modifier
                                    .size(40.dp)
                                    .constrainAs(btninfo) {
                                        top.linkTo(parent.top, 20.dp)
                                        end.linkTo(parent.end)
                                    }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Info,
                                    contentDescription = "Info",
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    tint = LightBlue
                                )
                            }
                        }
                    }

                    // Teaminfobereich
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.8f)
                            .constrainAs(teaminfo) {
                                bottom.linkTo(parent.bottom)
                                centerHorizontallyTo(parent)
                            }
                    ) {

                        Spacer(modifier = Modifier.height(20.dp))
                        // Pokemon One
                        if (currentTeam.pokemonOne == "") {
                            CardWithAnimatedBorder(
                                borderColors = PokemonEvoloutionBorder(currentTeam.pokemonOne),
                            ) {
                                PokemonTeamCardAddBearbeiten(navController = navController, context = context, clickeid = 1)
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        } else {
                            SwipeableCardBoth(context = context, id = 1, navController =navController, team = currentTeam, pokemon = currentTeam.pokemonOne) {
                                CardWithAnimatedBorder(
                                    borderColors = listOf(Color.White, Color.White),
                                ) {
                                    PokemonTeamCard(navController, currentTeam.pokemonOne)
                                }
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }

                        // Pokemon Two
                        if (currentTeam.pokemonTwo == "") {
                            CardWithAnimatedBorder(
                                borderColors = listOf(Color.White, Color.White),
                            ) {
                                PokemonTeamCardAddBearbeiten(navController= navController, context, clickeid = 2)
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        } else {
                            SwipeableCardBoth(context = context, id = 2, navController = navController, team = currentTeam, pokemon = currentTeam.pokemonTwo) {
                                CardWithAnimatedBorder(
                                    borderColors = PokemonEvoloutionBorder(currentTeam.pokemonTwo),
                                ) {
                                    PokemonTeamCard(navController, currentTeam.pokemonTwo)
                                }
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }

                        // Pokemon Three
                        if (currentTeam.pokemonThree == "") {
                            CardWithAnimatedBorder(
                                borderColors = listOf(Color.White, Color.White),
                            ) {
                                PokemonTeamCardAddBearbeiten(navController = navController, context = context, clickeid = 3)
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        } else {
                            SwipeableCardBoth(context = context, id = 3, navController =navController, team = currentTeam, pokemon = currentTeam.pokemonThree) {
                                CardWithAnimatedBorder(
                                    borderColors = PokemonEvoloutionBorder(currentTeam.pokemonThree),
                                ) {
                                    PokemonTeamCard(navController, currentTeam.pokemonThree)
                                }
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }

                        GradientButton(
                            onClick = {
                                if (viewModelTeam.currentTeam.value.teamName.isNotEmpty()
                                    && viewModelTeam.currentTeam.value.pokemonOne.isNotEmpty()
                                    && viewModelTeam.currentTeam.value.pokemonTwo.isNotEmpty()
                                    && viewModelTeam.currentTeam.value.pokemonThree.isNotEmpty()
                                ) {
                                    viewModelTeam.updateTeam(context)
                                    navController.navigate(Screen.Teamubersicht.route)
                                } else {
                                    messageDialogError(
                                        context,
                                        "Es müssen alle Pokemon ausgefüllt sein"
                                    )
                                }
                            },
                            text = stringResource(id = R.string.btnsave),
                            paddingx = 80.dp,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}

