package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.components.CardWithAnimatedBorder
import com.klimpel.abschlussarbeitmodul3.ui.components.GradientButton
import com.klimpel.abschlussarbeitmodul3.ui.components.PokemonTeamCard
import com.klimpel.abschlussarbeitmodul3.ui.components.messageDialogError
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
    val currentteam by remember { mutableStateOf(viewModelTeam.addTeam) }
    Log.e("TEAM_ERSTELLEN_TEST" , "$currentteam")
    if (currentteam.pokemonOne.isNotEmpty() && currentteam.pokemonTwo.isNotEmpty() && currentteam.pokemonThree.isNotEmpty()){
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

                        val pokemonList = viewModel.pokemonUbersicht.collectAsState()
                        val openPokemonAddDialog = remember { mutableStateOf(false) }
                        if (openPokemonAddDialog.value) {
                            Dialog(
                                onDismissRequest = { openPokemonAddDialog.value = false },
                                properties = DialogProperties(usePlatformDefaultWidth = false),
                            ) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .height(
                                            calcDp(
                                                percentage = 0.8f,
                                                dimension = Dimension.Height
                                            )
                                        ),
                                    colors = CardDefaults.cardColors(Color.White),
                                    shape = RoundedCornerShape(
                                        topStart = 50.dp,
                                        topEnd = 20.dp,
                                        bottomStart = 20.dp,
                                        bottomEnd = 50.dp
                                    ),
                                    elevation = CardDefaults.elevatedCardElevation(
                                        defaultElevation = 10.dp
                                    ),
                                ) {
                                    ConstraintLayout(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        val (titel, listViewPokemon, btnsave) = createRefs()
                                        // Profil Titel
                                        Card(
                                            colors = CardDefaults.cardColors(
                                                containerColor = Color.White,
                                            ),
                                            border = BorderStroke(
                                                4.dp, LightBlue
                                            ),
                                            modifier = Modifier
                                                .width(180.dp)
                                                .height(50.dp)
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
                                                    text = stringResource(id = R.string.titelpokemonchoice),
                                                    color = LightBlue,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    textAlign = TextAlign.Center,
                                                )
                                            }
                                        }

                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight(0.7f)
                                                .padding(horizontal = 20.dp)
                                                .constrainAs(listViewPokemon) {
                                                    top.linkTo(titel.bottom)
                                                    centerVerticallyTo(parent)
                                                }
                                        ) {
                                            LazyVerticalGrid(
                                                columns = GridCells.Adaptive(130.dp),
                                            ) {
                                                viewModel.loadOwnedPokemon()
                                                val itemCount = pokemonList.value.size
                                                Log.e("LISTE", "$itemCount")
                                                Log.e("LISTE", "$pokemonList")
                                                items(itemCount) {

                                                    AvailablePokemonRow(
                                                        navController  = navController,
                                                        clickedID = clickedID,
                                                        rowIndex = it,
                                                        entries = pokemonList.value
                                                    )

                                                    Spacer(modifier = Modifier.height(10.dp))
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(40.dp))
                        if (viewModelTeam.addTeam.pokemonOne == "") {

                            Card(
                                onClick = {
                                    openPokemonAddDialog.value = true
                                    clickedID = 1
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(
                                        calcDp(
                                            percentage = 0.15f,
                                            dimension = Dimension.Height
                                        )
                                    )
                                    .padding(4.dp),
                                colors = CardDefaults.cardColors(Color.White),
                                shape = RoundedCornerShape(
                                    topStart = 50.dp,
                                    topEnd = 20.dp,
                                    bottomStart = 20.dp,
                                    bottomEnd = 50.dp
                                ),
                                elevation = CardDefaults.elevatedCardElevation(
                                    defaultElevation = 10.dp
                                ),
                                border = BorderStroke(2.dp, LightBlue)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = "Add",
                                        modifier = Modifier
                                            .size(80.dp),
                                        tint = LightBlue
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        } else {
                            CardWithAnimatedBorder(
                                borderColors = PokemonEvoloutionBorder(viewModelTeam.addTeam.pokemonOne),
                            ) {
                                PokemonTeamCard(navController, viewModelTeam.addTeam.pokemonOne)
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }
                        if (viewModelTeam.addTeam.pokemonTwo == "") {

                            Card(
                                onClick = {
                                    openPokemonAddDialog.value = true
                                    clickedID = 2
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(
                                        calcDp(
                                            percentage = 0.15f,
                                            dimension = Dimension.Height
                                        )
                                    )
                                    .padding(4.dp),
                                colors = CardDefaults.cardColors(Color.White),
                                shape = RoundedCornerShape(
                                    topStart = 50.dp,
                                    topEnd = 20.dp,
                                    bottomStart = 20.dp,
                                    bottomEnd = 50.dp
                                ),
                                elevation = CardDefaults.elevatedCardElevation(
                                    defaultElevation = 10.dp
                                ),
                                border = BorderStroke(2.dp, LightBlue)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = "Add",
                                        modifier = Modifier
                                            .size(80.dp),
                                        tint = LightBlue
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        } else {
                            CardWithAnimatedBorder(
                                borderColors = PokemonEvoloutionBorder(viewModelTeam.addTeam.pokemonTwo),
                            ) {
                                PokemonTeamCard(navController, viewModelTeam.addTeam.pokemonTwo)
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        }
                        if (viewModelTeam.addTeam.pokemonThree == "") {

                            Card(
                                onClick = {
                                    openPokemonAddDialog.value = true
                                    clickedID = 3
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(
                                        calcDp(
                                            percentage = 0.15f,
                                            dimension = Dimension.Height
                                        )
                                    )
                                    .padding(4.dp),
                                colors = CardDefaults.cardColors(Color.White),
                                shape = RoundedCornerShape(
                                    topStart = 50.dp,
                                    topEnd = 20.dp,
                                    bottomStart = 20.dp,
                                    bottomEnd = 50.dp
                                ),
                                elevation = CardDefaults.elevatedCardElevation(
                                    defaultElevation = 10.dp
                                ),
                                border = BorderStroke(2.dp, LightBlue)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = "Add",
                                        modifier = Modifier
                                            .size(80.dp),
                                        tint = LightBlue
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        } else {
                            CardWithAnimatedBorder(
                                borderColors = PokemonEvoloutionBorder(viewModelTeam.addTeam.pokemonThree),
                            ) {
                                PokemonTeamCard(navController, viewModelTeam.addTeam.pokemonThree)
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        }


                        GradientButton(
                            onClick = {
                                viewModelTeam.addTeam.teamName = textStateTeamName.text
                                if (viewModelTeam.addTeam.teamName.isNotEmpty()
                                    && viewModelTeam.addTeam.pokemonOne.isNotEmpty()
                                    && viewModelTeam.addTeam.pokemonTwo.isNotEmpty()
                                    && viewModelTeam.addTeam.pokemonThree.isNotEmpty()
                                ) {
                                    viewModelTeam.addTeam(context)
                                    viewModelTeam.deleteCurrentTeam()
                                    navController.navigate(Screen.Teamubersicht.route)
                                }else {
                                    messageDialogError(context, "Es müssen alle Pokemon ausgefüllt sein")
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
