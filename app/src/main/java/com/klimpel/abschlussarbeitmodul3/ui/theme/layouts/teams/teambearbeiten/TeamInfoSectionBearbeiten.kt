package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teambearbeiten

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.ui.components.CardWithAnimatedBorder
import com.klimpel.abschlussarbeitmodul3.ui.components.PokemonTeamCard
import com.klimpel.abschlussarbeitmodul3.ui.components.PokemonTeamCardAddBearbeiten
import com.klimpel.abschlussarbeitmodul3.ui.components.swipeableelements.SwipeableCardBoth
import com.klimpel.abschlussarbeitmodul3.util.pokemonEvoloutionBorder

@Composable
fun TeamInfoSectionBearbeiten(context: Context, navController: NavController, currentTeam: BattleTeams){

    Spacer(modifier = Modifier.height(20.dp))
    // Pokemon One
    if (currentTeam.pokemonOne == "") {
        CardWithAnimatedBorder(
            borderColors = pokemonEvoloutionBorder(currentTeam.pokemonOne),
        ) {
            PokemonTeamCardAddBearbeiten(navController = navController, context = context, clickeid = 1)
        }
        Spacer(modifier = Modifier.height(40.dp))
    } else {
        SwipeableCardBoth(context = context, id = 1, navController =navController, team = currentTeam, pokemon = currentTeam.pokemonOne) {
            CardWithAnimatedBorder(
                borderColors = listOf(Color.White, Color.White),
            ) {
                PokemonTeamCard(navController, currentTeam.pokemonOne)
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }

    // Pokemon Two
    if (currentTeam.pokemonTwo == "") {
        CardWithAnimatedBorder(
            borderColors = listOf(Color.White, Color.White),
        ) {
            PokemonTeamCardAddBearbeiten(navController= navController, context, clickeid = 2)
        }
        Spacer(modifier = Modifier.height(40.dp))
    } else {
        SwipeableCardBoth(context = context, id = 2, navController = navController, team = currentTeam, pokemon = currentTeam.pokemonTwo) {
            CardWithAnimatedBorder(
                borderColors = pokemonEvoloutionBorder(currentTeam.pokemonTwo),
            ) {
                PokemonTeamCard(navController, currentTeam.pokemonTwo)
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }

    // Pokemon Three
    if (currentTeam.pokemonThree == "") {
        CardWithAnimatedBorder(
            borderColors = listOf(Color.White, Color.White),
        ) {
            PokemonTeamCardAddBearbeiten(navController = navController, context = context, clickeid = 3)
        }
        Spacer(modifier = Modifier.height(40.dp))
    } else {
        SwipeableCardBoth(context = context, id = 3, navController =navController, team = currentTeam, pokemon = currentTeam.pokemonThree) {
            CardWithAnimatedBorder(
                borderColors = pokemonEvoloutionBorder(currentTeam.pokemonThree),
            ) {
                PokemonTeamCard(navController, currentTeam.pokemonThree)
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}