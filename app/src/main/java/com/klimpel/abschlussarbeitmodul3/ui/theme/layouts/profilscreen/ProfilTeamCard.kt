package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.ui.components.TitelCardString
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilTeamCard(
    battleTeams: BattleTeams,
    navController: NavController,
    viewModelteam: TeamViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val openDeleteDialog = remember { mutableStateOf(false) }

    if (openDeleteDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDeleteDialog.value = false
            },
            title = {
                Text(text = "Team Löschen")
            },
            text = {
                Text(text = "Sind Sie sicher das sie das Team : ${battleTeams.teamName} löschen wollen?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModelteam.deleteTeam(context, battleTeams)
                        openDeleteDialog.value = false
                        navController.navigate(Screen.ProfilScreen.route)
                    }
                ) {
                    Text("Löschen", color = DeepRed, fontWeight = FontWeight.Bold)
                }
            },
            tonalElevation = AlertDialogDefaults.TonalElevation,
            dismissButton = {
                TextButton(
                    onClick = {
                        openDeleteDialog.value = false
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
            ),
            modifier = Modifier
                .border(
                    4.dp,
                    DeepRed,
                    shape = RoundedCornerShape(
                        topStart = 50.dp,
                        topEnd = 20.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 50.dp
                    )
                )
        )
    }

    Card(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(
            topStart = 50.dp,
            topEnd = 20.dp,
            bottomStart = 20.dp,
            bottomEnd = 50.dp
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        border = BorderStroke(2.dp, LightBlue),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            TitelCardString(titel = battleTeams.teamName)
            IconButton(onClick = { openDeleteDialog.value = true }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete Team",
                    tint = DeepRed,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(35.dp)
                )
            }
        }
        TeamCardPokemonProfil(battleTeams, navController)
    }
}