package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel

@Composable
fun TeamInfoTopSection(context: Context, battleTeams: BattleTeams, navController: NavController, viewModelTeam: TeamViewModel= hiltViewModel()){

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
                Text(text = "Sind Sie sicher das sie das Team : ${battleTeams.teamName} löschen wollen?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModelTeam.deleteTeam(context, battleTeams.teamName)
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
                text = battleTeams.teamName,
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
}