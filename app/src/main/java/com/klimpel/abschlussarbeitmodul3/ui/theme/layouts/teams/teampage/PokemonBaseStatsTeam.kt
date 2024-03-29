package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.util.parseStatToColorString

@Composable
fun PokemonBaseStatsTeam(
    teams: BattleTeams,
    animDelayPerItem: Int = 100
) {
    var maxBaseStat = 5
    val testliste = listOf(teams.hp, teams.atk, teams.def, teams.spatk, teams.spdef,teams.spd)
    if (testliste.max() != 0){
        maxBaseStat = testliste.max()
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        PokemonStatTeamOhneText(
            statValue = teams.hp,
            statMaxValue = maxBaseStat,
            statColor = parseStatToColorString("hp"),
            animDelay = 1 * animDelayPerItem
        )
        Spacer(modifier = Modifier.height(8.dp))

        PokemonStatTeamOhneText(
            statValue = teams.atk,
            statMaxValue = maxBaseStat,
            statColor = parseStatToColorString("attack"),
            animDelay = 2 * animDelayPerItem
        )
        Spacer(modifier = Modifier.height(8.dp))
        PokemonStatTeamOhneText(
            statValue = teams.def,
            statMaxValue = maxBaseStat,
            statColor = parseStatToColorString("defense"),
            animDelay = 3 * animDelayPerItem
        )
        Spacer(modifier = Modifier.height(8.dp))
        PokemonStatTeamOhneText(
            statValue = teams.spatk,
            statMaxValue = maxBaseStat,
            statColor = parseStatToColorString("special-attack"),
            animDelay = 4 * animDelayPerItem
        )
        Spacer(modifier = Modifier.height(8.dp))
        PokemonStatTeamOhneText(
            statValue = teams.spdef,
            statMaxValue = maxBaseStat,
            statColor = parseStatToColorString("special-defense"),
            animDelay = 5 * animDelayPerItem
        )
        Spacer(modifier = Modifier.height(8.dp))
        PokemonStatTeamOhneText(
            statValue = teams.spd,
            statMaxValue = maxBaseStat,
            statColor = parseStatToColorString("speed"),
            animDelay = 6 * animDelayPerItem
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}