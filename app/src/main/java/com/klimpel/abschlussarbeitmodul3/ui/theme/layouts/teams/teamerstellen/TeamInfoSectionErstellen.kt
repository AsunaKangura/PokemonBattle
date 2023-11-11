package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teamerstellen

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
import com.klimpel.abschlussarbeitmodul3.ui.components.PokemonTeamCardAddErstellen
import com.klimpel.abschlussarbeitmodul3.util.PokemonEvoloutionBorder

@Composable
fun TeamInfoSectionErstellen(context: Context, navController: NavController, battleTeams: BattleTeams){

    Spacer(modifier = Modifier.height(40.dp))

    if (battleTeams.pokemonOne == "") {
        CardWithAnimatedBorder(
            borderColors = PokemonEvoloutionBorder(battleTeams.pokemonOne),
        ) {
            PokemonTeamCardAddErstellen(
                navController = navController,
                context = context,
                clickeid = 1
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    } else {
        CardWithAnimatedBorder(
            borderColors = listOf(Color.White, Color.White),
        ) {
            PokemonTeamCard(navController, battleTeams.pokemonOne)
        }
        Spacer(modifier = Modifier.height(30.dp))
    }

    if (battleTeams.pokemonTwo == "") {
        CardWithAnimatedBorder(
            borderColors = PokemonEvoloutionBorder(battleTeams.pokemonTwo),
        ) {
            PokemonTeamCardAddErstellen(
                navController = navController,
                context = context,
                clickeid = 2
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    } else {
        CardWithAnimatedBorder(
            borderColors = listOf(Color.White, Color.White),
        ) {
            PokemonTeamCard(navController, battleTeams.pokemonTwo)
        }
        Spacer(modifier = Modifier.height(30.dp))
    }

    if (battleTeams.pokemonThree == "") {
        CardWithAnimatedBorder(
            borderColors = PokemonEvoloutionBorder(battleTeams.pokemonThree),
        ) {
            PokemonTeamCardAddErstellen(
                navController = navController,
                context = context,
                clickeid = 3
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    } else {
        CardWithAnimatedBorder(
            borderColors = listOf(Color.White, Color.White),
        ) {
            PokemonTeamCard(navController, battleTeams.pokemonThree)
        }
        Spacer(modifier = Modifier.height(30.dp))
    }

}