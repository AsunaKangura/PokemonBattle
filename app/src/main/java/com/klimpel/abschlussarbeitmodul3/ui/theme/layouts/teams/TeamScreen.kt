package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams


import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.ui.components.CardWithAnimatedBorder
import com.klimpel.abschlussarbeitmodul3.ui.components.PokemonTeamCard
import com.klimpel.abschlussarbeitmodul3.ui.components.TeamCard
import com.klimpel.abschlussarbeitmodul3.ui.components.TeamCardAdd
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.topappbars.TopAppBarTitelBackArrowTeamUbersicht
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.PokemonEvoloutionBorder
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel
import com.klimpel.pokemonbattlefinal.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreen(
    navController: NavController,
    viewModelTeam: TeamViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val teamubersichtlist = viewModelTeam.teamubersicht.collectAsState()

    val currentTeam by viewModelTeam.currentTeam.collectAsState()
    val currentUser = viewModelTeam.currentUser


    val openDeleteAlertDialog = remember { mutableStateOf(false) }
    if (openDeleteAlertDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDeleteAlertDialog.value = false
            },
            title = {
                Text(text = "Team Löschen")
            },
            text = {
                Text(text = "Sind Sie sicher das sie das Team : ${currentTeam.teamName} löschen wollen?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModelTeam.deleteTeam(context, currentTeam.teamName)
                        viewModelTeam.deleteAktivTeam(context)
                        openDeleteAlertDialog.value = false
                        navController.navigate(Screen.Teamubersicht.route)
                    }
                ) {
                    Text("Löschen")
                }
            },
            tonalElevation = AlertDialogDefaults.TonalElevation,
            dismissButton = {
                TextButton(
                    onClick = {
                        openDeleteAlertDialog.value = false
                    }
                ) {
                    Text("Abbrechen")
                }
            },
            shape = RoundedCornerShape(
                topStart = 50.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 50.dp
            )
        )
    }

    Log.e("TESTLOG_TEAM_UBERSICHT" ,"$currentTeam")

    AbschlussarbeitModul3Theme {
        Scaffold(
            topBar = {
                TopAppBarTitelBackArrowTeamUbersicht(
                    pageTitle = R.string.titelTeamScreen,
                    navController = navController
                )
            },
            containerColor = LightBlueBackground,
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {

                    val (dividertopappbar, dividercarouselltop, dividercarousellbottom, teamtitel, teamcarousell, teaminfo) = createRefs()

                    Divider(
                        thickness = 4.dp,
                        color = LightBlue,
                        modifier = Modifier.constrainAs(dividertopappbar) { top.linkTo(parent.top) }
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .constrainAs(teamtitel) {
                                top.linkTo(dividertopappbar.bottom, 10.dp)
                            }
                            .fillMaxWidth()
                            .height(calcDp(percentage = 0.05f, dimension = Dimension.Height))
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .fillMaxHeight()
                        ) {
                            Text(text = "Eigene Teams", color = LightBlue)
                            Text(
                                text = "${viewModelTeam.currentUser?.teams} / 10",
                                color = LightBlue
                            )
                        }
                    }

                    Divider(
                        thickness = 1.dp,
                        color = LightBlue,
                        modifier = Modifier.constrainAs(dividercarouselltop) {
                            bottom.linkTo(
                                teamcarousell.top
                            )
                        }
                    )

                    // Übersicht der Teams
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .constrainAs(teamcarousell) {
                                top.linkTo(teamtitel.bottom)
                            }
                            .fillMaxWidth()
                            .fillMaxHeight(0.1f)
                            .background(Color.White)
                    ) {

                        viewModelTeam.loadTeamubersicht()

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            val itemcount = teamubersichtlist.value.size
                            item { Spacer(modifier = Modifier.width(10.dp)) }

                            items(itemcount) {
                                if (teamubersichtlist.value[it].teamName != "") {
                                    TeamCard(pokemonTeamCard = teamubersichtlist.value[it])
                                }
                            }

                            item {
                                Spacer(modifier = Modifier.width(10.dp))
                                if (viewModelTeam.currentUser?.teams != 10) {
                                    TeamCardAdd({ navController.navigate(Screen.Teamerstellen.route) })
                                }
                            }

                            item { Spacer(modifier = Modifier.width(10.dp)) }
                        }
                    }

                    Divider(
                        thickness = 1.dp,
                        color = LightBlue,
                        modifier = Modifier.constrainAs(dividercarousellbottom) {
                            top.linkTo(teamcarousell.bottom)
                        })

                    // Teaminfobereich
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.9f)
                            .constrainAs(teaminfo) {
                                top.linkTo(teamcarousell.bottom, 50.dp)
                                centerHorizontallyTo(parent)
                            }
                    ) {
                        if (currentUser?.aktivteam != "null") {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(0.6f)
                                        .height(40.dp)
                                ) {
                                    Text(
                                        text = currentTeam.teamName,
                                        fontSize = 26.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = pokemonFontFamily,
                                        color = LightBlue
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.End,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        IconButton(
                                            onClick = { navController.navigate(Screen.Teambearbeiten.route) },
                                            modifier = Modifier
                                                .size(40.dp)
                                        ) {
                                            Icon(
                                                Icons.Outlined.Edit,
                                                contentDescription = "edit",
                                                tint = LightBlue
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        IconButton(
                                            onClick = {
                                                openDeleteAlertDialog.value = true
                                            },
                                            modifier = Modifier
                                                .size(40.dp),

                                            ) {
                                            Icon(
                                                Icons.Outlined.DeleteOutline,
                                                contentDescription = "delete",
                                                tint = LightBlue
                                            )
                                        }
                                    }
                                }

                            }
                            Divider(thickness = 1.dp, color = LightBlue)
                            Spacer(modifier = Modifier.height(30.dp))

                            TeamInfoDetail(
                                navController = navController,
                                battleTeams = currentTeam
                            )

                        } else {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 10.dp)
                            ) {
                                Text(
                                    text = "Du hast noch keine",
                                    fontSize = 28.sp,
                                    color = LightBlue,
                                    fontFamily = pokemonFontFamily
                                )
                                Text(
                                    text = "eigenen Teams angelegt.",
                                    fontSize = 28.sp,
                                    color = LightBlue,
                                    fontFamily = pokemonFontFamily
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun TeamInfoDetail(
    navController: NavController,
    battleTeams: BattleTeams,
    viewModelTeam: TeamViewModel = hiltViewModel()
) {

    val pokemonOneName = battleTeams.pokemonOne
    val pokemonTwoName = battleTeams.pokemonTwo
    val pokemonThreeName = battleTeams.pokemonThree

    LazyColumn {
        item {
            if (battleTeams.teamName != "") {
                CardWithAnimatedBorder(
                    borderColors = PokemonEvoloutionBorder(pokemonOneName)
                ) {
                    PokemonTeamCard(navController, pokemonOneName)
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
        item {
            if (battleTeams.teamName != "") {
                CardWithAnimatedBorder(
                    borderColors = PokemonEvoloutionBorder(pokemonTwoName)
                ) {
                    PokemonTeamCard(navController, pokemonTwoName)
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
        item {
            if (battleTeams.teamName != "") {
                CardWithAnimatedBorder(
                    borderColors = PokemonEvoloutionBorder(pokemonThreeName)
                ) {
                    PokemonTeamCard(navController, pokemonThreeName)
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }

}




