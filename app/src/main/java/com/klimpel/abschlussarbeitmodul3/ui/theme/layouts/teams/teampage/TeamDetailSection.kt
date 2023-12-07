package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.view.WindowInsetsAnimationCompat.BoundsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.pokemondetailscreen.PokemonBaseStats
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.statDialog.CustomDialogPosition
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.statDialog.DialogStateWrapper
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.statDialog.customDialogModifier
import com.klimpel.abschlussarbeitmodul3.util.Resource
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel


@Composable
fun TeamDetailSection(
    teams: BattleTeams,
    teamStats: Resource<BattleTeams?>?,
    modifier: Modifier = Modifier,
    viewModelteam: TeamViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()


    val openStatDialog = remember { mutableStateOf(false) }
    if (openStatDialog.value) {
        Dialog(
            onDismissRequest = { openStatDialog.value = false }
        ) {
            DialogStateWrapper(
                teamStats = teamStats,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.36f)
                    .padding(
                        top = 4.dp,
                        start = 0.dp,
                        end = 4.dp,
                        bottom = 0.dp
                    )
                    .shadow(10.dp, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(14.dp),
                loadingModifier = Modifier
                    .size(100.dp)
                    .padding(
                        top = 8.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 0.dp
                    )
            )
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .offset(y = 20.dp)
            .verticalScroll(scrollState)
            .clickable { openStatDialog.value = true }
    ) {
        PokemonBaseStatsTeam(teams = teams)
    }
}