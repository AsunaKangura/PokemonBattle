package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teamubersicht

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.ui.components.CardWithAnimatedBorder
import com.klimpel.abschlussarbeitmodul3.ui.components.PokemonTeamCard
import com.klimpel.abschlussarbeitmodul3.ui.components.swipeableelements.SwipeableCardLeft
import com.klimpel.abschlussarbeitmodul3.util.PokemonEvoloutionBorder
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel

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
                val route = "PokemonDetailScreen/${battleTeams.pokemonOne}"
                SwipeableCardLeft(navController = navController, route = route) {
                    CardWithAnimatedBorder(
                        borderColors = PokemonEvoloutionBorder(pokemonOneName)
                    ) {
                        PokemonTeamCard(navController, pokemonOneName)
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
        item {
            if (battleTeams.teamName != "") {
                val route = "PokemonDetailScreen/${battleTeams.pokemonTwo}"
                SwipeableCardLeft(navController = navController, route = route ) {
                    CardWithAnimatedBorder(
                        borderColors = PokemonEvoloutionBorder(pokemonTwoName)
                    ) {
                        PokemonTeamCard(navController, pokemonTwoName)
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
        item {
            if (battleTeams.teamName != "") {
                val route = "PokemonDetailScreen/${battleTeams.pokemonThree}"
                SwipeableCardLeft(navController = navController, route = route) {
                    CardWithAnimatedBorder(
                        borderColors = PokemonEvoloutionBorder(pokemonThreeName)
                    ) {
                        PokemonTeamCard(navController, pokemonThreeName)
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}