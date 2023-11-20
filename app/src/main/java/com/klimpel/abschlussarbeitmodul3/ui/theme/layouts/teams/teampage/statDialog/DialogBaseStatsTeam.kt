package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.statDialog


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.PokemonStatTeam
import com.klimpel.abschlussarbeitmodul3.util.parseStatToAbbr2
import com.klimpel.abschlussarbeitmodul3.util.parseStatToColor2

@Composable
fun DialogBaseStatsTeam(
    teams: BattleTeams,
    //pokemonInfo: Pokemon,
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
        Text(
            text = "Team Stats:",
            fontSize = 20.sp,
            color = LightBlue,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            PokemonStatTeam(
                statName = parseStatToAbbr2("hp"),
                statValue = teams.hp,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor2("hp"),
                animDelay = 1 * animDelayPerItem
            )
            Spacer(modifier = Modifier.height(8.dp))

            PokemonStatTeam(
                statName = parseStatToAbbr2("attack"),
                statValue = teams.atk,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor2("attack"),
                animDelay = 2 * animDelayPerItem
            )
            Spacer(modifier = Modifier.height(8.dp))
            PokemonStatTeam(
                statName = parseStatToAbbr2("defense"),
                statValue = teams.def,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor2("defense"),
                animDelay = 3 * animDelayPerItem
            )
            Spacer(modifier = Modifier.height(8.dp))
            PokemonStatTeam(
                statName = parseStatToAbbr2("special-attack"),
                statValue = teams.spatk,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor2("special-attack"),
                animDelay = 4 * animDelayPerItem
            )
            Spacer(modifier = Modifier.height(8.dp))
            PokemonStatTeam(
                statName = parseStatToAbbr2("special-defense"),
                statValue = teams.spdef,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor2("special-defense"),
                animDelay = 5 * animDelayPerItem
            )
            Spacer(modifier = Modifier.height(8.dp))
            PokemonStatTeam(
                statName = parseStatToAbbr2("speed"),
                statValue = teams.spd,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor2("speed"),
                animDelay = 6 * animDelayPerItem
            )
            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}