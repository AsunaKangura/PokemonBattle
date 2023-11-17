package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TeamCardPokemon(
    battleTeams: BattleTeams,
    navController: NavController,
    viewModelteam: TeamViewModel = hiltViewModel(),
){
    val scope = rememberCoroutineScope()
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable {
                viewModelteam.deleteCurrentTeam()
                viewModelteam.loadTeam(battleTeams.teamName)
                scope.launch {
                    delay(500)
                    navController.navigate("TeamSeite/${battleTeams.teamName}")
                }
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(120.dp)
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            PokemonCardTeamUbersichtPokemonOne(navController, battleTeams.pokemonOne, battleTeams)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(120.dp)
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            Spacer(modifier = Modifier.height(0.dp))
            PokemonCardTeamUbersichtPokemonOne(navController, battleTeams.pokemonTwo, battleTeams)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(120.dp)
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            PokemonCardTeamUbersichtPokemonOne(navController, battleTeams.pokemonThree, battleTeams)
        }
    }
}