package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.statDialog

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.util.Resource

@Composable
fun DialogStateWrapper(
    teamStats: Resource<BattleTeams?>?,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier
) {
    when(teamStats){
        is Resource.Success -> {
            teamStats.data?.let {
                DialogTeamDetailSection(
                    teams = it,
                    modifier = modifier
                        .offset(y = (-20).dp)
                )
            }
        }
        is Resource.Error -> {
            teamStats.message?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = modifier
                )
            }
        }
        is Resource.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = loadingModifier
            )
        }

        else ->{}
    }
}