package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams


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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.components.CardWithAnimatedBorder
import com.klimpel.abschlussarbeitmodul3.ui.components.GradientButton
import com.klimpel.abschlussarbeitmodul3.ui.components.PokemonTeamCard
import com.klimpel.abschlussarbeitmodul3.ui.components.PokemonTeamCardAddBearbeiten
import com.klimpel.abschlussarbeitmodul3.ui.components.PokemonTeamCardAddErstellen
import com.klimpel.abschlussarbeitmodul3.ui.components.messageDialogError
import com.klimpel.abschlussarbeitmodul3.ui.components.swipeableelements.SwipeableCardBoth
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.topappbars.TopAppBarTitelBackArrowTeamErstellen
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.PokemonEvoloutionBorder
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.MeinePokemonViewModel
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel
import com.klimpel.pokemonbattlefinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamErstellenScreen(
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
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Teamname eingeben",
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
                                        text = "Bei der Eingabe, muss folgendes beachtet werden: \n- keine Leerzeichen enthalten",
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                    )
                                }
                            }
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

    val enabelTeamName = remember { mutableStateOf(false) }

    val currentteam by remember { mutableStateOf(viewModelTeam.currentTeam.value) }

    if (currentteam.pokemonOne.isNotEmpty() && currentteam.pokemonTwo.isNotEmpty() && currentteam.pokemonThree.isNotEmpty()) {
        enabelTeamName.value = true
    }

    var clickedID = 0
    val context = LocalContext.current

    var textStateTeamName by remember { mutableStateOf(TextFieldValue("")) }

    AbschlussarbeitModul3Theme {
        Scaffold(
            topBar = {
                TopAppBarTitelBackArrowTeamErstellen(
                    pageTitle = R.string.titelTeamErstellenScreen,
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
                                text = "TeamInfos",
                                modifier = Modifier
                                    .constrainAs(teaminfotext) {
                                        bottom.linkTo(inputteamname.top, 10.dp)
                                    },
                                fontSize = 28.sp,
                                color = LightBlue,
                                fontWeight = FontWeight.Bold,
                                fontFamily = pokemonFontFamily
                            )

                            OutlinedTextField(
                                value = textStateTeamName,
                                onValueChange = { textStateTeamName = it },
                                enabled = enabelTeamName.value,
                                label = {
                                    Text(
                                        "Teamname eingeben",
                                        fontSize = 14.sp,
                                    )
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = LightBlue,
                                    unfocusedBorderColor = Color.Black,
                                    focusedLabelColor = LightBlue,
                                    unfocusedLabelColor = Color.Black,
                                    textColor = Color.Black
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .constrainAs(inputteamname) {
                                        bottom.linkTo(parent.bottom)
                                        //centerHorizontallyTo(parent)
                                    }
                                    .padding(horizontal = 0.dp)

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

                        Spacer(modifier = Modifier.height(40.dp))

                        if (currentteam.pokemonOne == "") {
                            CardWithAnimatedBorder(
                                borderColors = PokemonEvoloutionBorder(currentteam.pokemonOne),
                            ) {
                                PokemonTeamCardAddErstellen(
                                    navController = navController,
                                    context = context,
                                    clickeid = 1
                                )
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        } else {
                            CardWithAnimatedBorder(
                                borderColors = listOf(Color.White, Color.White),
                            ) {
                                PokemonTeamCard(navController, currentteam.pokemonOne)
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }

                        if (currentteam.pokemonTwo == "") {
                            CardWithAnimatedBorder(
                                borderColors = PokemonEvoloutionBorder(currentteam.pokemonTwo),
                            ) {
                                PokemonTeamCardAddErstellen(
                                    navController = navController,
                                    context = context,
                                    clickeid = 2
                                )
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        } else {
                            CardWithAnimatedBorder(
                                borderColors = listOf(Color.White, Color.White),
                            ) {
                                PokemonTeamCard(navController, currentteam.pokemonTwo)
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }

                        if (currentteam.pokemonThree == "") {
                            CardWithAnimatedBorder(
                                borderColors = PokemonEvoloutionBorder(currentteam.pokemonThree),
                            ) {
                                PokemonTeamCardAddErstellen(
                                    navController = navController,
                                    context = context,
                                    clickeid = 3
                                )
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        } else {
                            CardWithAnimatedBorder(
                                borderColors = listOf(Color.White, Color.White),
                            ) {
                                PokemonTeamCard(navController, currentteam.pokemonThree)
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }

                        GradientButton(
                            onClick = {
                                currentteam.teamName = textStateTeamName.text
                                if (currentteam.teamName.isNotEmpty()
                                    && currentteam.pokemonOne.isNotEmpty()
                                    && currentteam.pokemonTwo.isNotEmpty()
                                    && currentteam.pokemonThree.isNotEmpty()
                                ) {
                                    viewModelTeam.addTeam(context)
                                    viewModelTeam.deleteCurrentTeam()
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
