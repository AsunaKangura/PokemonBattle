package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teambearbeiten


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.components.GradientButton
import com.klimpel.abschlussarbeitmodul3.ui.components.messageDialogError
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.topappbars.TopAppBarTitelBackArrowTeamBearbeiten
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

    var clickedID = 0
    val context = LocalContext.current

    val currentTeam by viewModelTeam.currentTeam.collectAsStateWithLifecycle()

    AbschlussarbeitModul3Theme {
        Scaffold(
            topBar = { TopAppBarTitelBackArrowTeamBearbeiten(pageTitle = R.string.titelTeamBearbeitenScreen, navController = navController) },
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

                    Divider(thickness = 4.dp, color = LightBlue, modifier = Modifier.constrainAs(dividertopappbar) { top.linkTo(parent.top) })

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.2f)
                            .constrainAs(teamname) {
                                centerHorizontallyTo(parent)
                                top.linkTo(dividertopappbar.bottom)
                            }
                    ) {
                        TeamBearbeitenTopSection()
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

                        TeamInfoSectionBearbeiten(context = context, navController = navController, currentTeam = currentTeam)

                        GradientButton(
                            onClick = {
                                if (viewModelTeam.currentTeam.value.teamName.isNotEmpty() && viewModelTeam.currentTeam.value.pokemonOne.isNotEmpty() && viewModelTeam.currentTeam.value.pokemonTwo.isNotEmpty() && viewModelTeam.currentTeam.value.pokemonThree.isNotEmpty()
                                ) {
                                    viewModelTeam.updateTeam(context)
                                    navController.navigate(Screen.Teamubersicht.route)
                                } else {
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

