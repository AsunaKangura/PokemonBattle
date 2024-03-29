package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.statDialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams

@Composable
fun DialogTeamDetailSection(
    teams: BattleTeams,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .offset(y = 25.dp)
            .verticalScroll(scrollState)
            .clickable { }
    ) {
        DialogBaseStatsTeam(teams = teams)
    }
}