package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teamerstellen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.klimpel.abschlussarbeitmodul3.ui.components.EditTextFieldEnabeled
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel

@Composable
fun TeamErstellenTopSection(
    value: String,
    onValueChange: (string: String) -> Unit,
    enabelTeamName: Boolean,
    viewModelTeam: TeamViewModel = hiltViewModel()
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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) {
            Box(modifier = Modifier.fillMaxWidth(0.5f)) {
                Text(
                    text = "TeamInfos",
                    fontSize = 28.sp,
                    color = LightBlue,
                    fontWeight = FontWeight.Bold,
                    fontFamily = pokemonFontFamily
                )
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                IconButton(
                    // Onlick Open Dialog für die Informationen.
                    onClick = {
                        openInfoDialog.value = true
                    },
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
        EditTextFieldEnabeled(
            value = value,
            onValueChange = onValueChange,
            label = "Teamname eingeben",
            enabelTeamName
        )
    }
}