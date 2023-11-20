package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teamerstellen


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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.components.GradientButton
import com.klimpel.abschlussarbeitmodul3.ui.components.messageDialogError
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.topappbars.TopAppBarTitelBackArrowTeamErstellen
import com.klimpel.abschlussarbeitmodul3.viewmodels.MeinePokemonViewModel
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel
import com.klimpel.pokemonbattlefinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamErstellenScreen2(
    navController: NavController,
    viewModelTeam: TeamViewModel = hiltViewModel(),
    viewModel: MeinePokemonViewModel = hiltViewModel(),
) {
    //viewModelTeam.deleteCurrentTeam()

    // hier wird der dezeitige Context gespeichert damit man die Toast ausführen kann.
    val context = LocalContext.current

    // Hier wird das derzeitge erstellt Team gespeichert ( Name, alle Pokemone) Die sind Livedata über das Repository
    val addteam by remember { mutableStateOf(viewModelTeam.addTeam.value) }// Dieser RememberState wird verwendet um zu Checken wann das Textfeld für den Teamnamen aktiviert wird

    // Dieser RememberState wird verwendet um zu Checken wann das Textfeld für den Teamnamen aktiviert wird
    val enabelTeamName = remember { mutableStateOf(false) }
    // Diese Abfrage aktiviert das Textfeld und ändert wenn die Bedingung erfüllt ist die Variable
    if (addteam.pokemonOne.isNotEmpty() && addteam.pokemonTwo.isNotEmpty() && addteam.pokemonThree.isNotEmpty()) {
        enabelTeamName.value = true
    }

    // hier wird die Eingabe des User gespeichert.
    var textStateTeamName by remember { mutableStateOf("") }

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
                        modifier = Modifier.constrainAs(dividertopappbar) { top.linkTo(parent.top) })

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.2f)
                            .constrainAs(teamname) {
                                centerHorizontallyTo(parent)
                                top.linkTo(dividertopappbar.bottom)
                            }

                    ) {
                        TeamErstellenTopSection(
                            value = textStateTeamName,
                            onValueChange = { values -> textStateTeamName = values },
                            enabelTeamName = enabelTeamName.value
                        )
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

                        TeamInfoSectionErstellen(
                            context = context,
                            navController = navController,
                            battleTeams = addteam
                        )

                        GradientButton(
                            onClick = {
                                viewModelTeam.teamnamehinzufugenAddTeam(textStateTeamName)
                                if (addteam.teamName.isNotEmpty() && addteam.pokemonOne.isNotEmpty() && addteam.pokemonTwo.isNotEmpty() && addteam.pokemonThree.isNotEmpty()){
                                    viewModelTeam.teamhinzufugen(context)
                                    navController.navigate(Screen.ProfilScreen.route)
                                }else {
                                    messageDialogError(context, "Es müssem ein Teamname vergeben werden")
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
