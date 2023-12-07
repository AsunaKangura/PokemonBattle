package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teamerstellen.PokemonTypeItem
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.createPokemon
import com.klimpel.abschlussarbeitmodul3.util.Resource
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
    val currentAktivteam = viewModelteam.currentTeam.collectAsState()


    val pokemonInfoPokemonOne = produceState<Resource<Pokemon>>(initialValue = Resource.Loading())
    {
        value = viewModelteam.getPokemonInfo(currentAktivteam.value.pokemonOne)
    }.value
    val pokemonInfoPokemonTwo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading())
    {
        value = viewModelteam.getPokemonInfo(currentAktivteam.value.pokemonTwo)
    }.value
    val pokemonInfoPokemonThree = produceState<Resource<Pokemon>>(initialValue = Resource.Loading())
    {
        value = viewModelteam.getPokemonInfo(currentAktivteam.value.pokemonThree)
    }.value

    val pokemonOne = createPokemon(pokemonInfoPokemonOne)
    val pokemonTwo = createPokemon(pokemonInfoPokemonTwo)
    val pokemonThree = createPokemon(pokemonInfoPokemonThree)



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
            //verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(110.dp)
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            PokemonCardTeamUbersichtPokemonOne(navController, battleTeams.pokemonOne, battleTeams)
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            ){
                if (pokemonOne != null) {
                    PokemonTypeItem(types = pokemonOne.types)
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(110.dp)
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            Spacer(modifier = Modifier.height(0.dp))
            PokemonCardTeamUbersichtPokemonOne(navController, battleTeams.pokemonTwo, battleTeams)
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){
                if (pokemonTwo != null) {
                    PokemonTypeItem(types = pokemonTwo.types)
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(110.dp)
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            PokemonCardTeamUbersichtPokemonOne(navController, battleTeams.pokemonThree, battleTeams)
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){
                if (pokemonThree != null) {
                    PokemonTypeItem(types = pokemonThree.types)
                }
            }
        }
    }
}

@Composable
fun TeamCardPokemonProfil(
    battleTeams: BattleTeams,
    navController: NavController,
    viewModelteam: TeamViewModel = hiltViewModel(),
){
    val scope = rememberCoroutineScope()
    val currentAktivteam = viewModelteam.currentTeam.collectAsState()

    /*
    val pokemonInfoPokemonOne = produceState<Resource<Pokemon>>(initialValue = Resource.Loading())
    {
        value = viewModelteam.getPokemonInfo(currentAktivteam.value.pokemonOne)
    }.value
    val pokemonInfoPokemonTwo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading())
    {
        value = viewModelteam.getPokemonInfo(currentAktivteam.value.pokemonTwo)
    }.value
    val pokemonInfoPokemonThree = produceState<Resource<Pokemon>>(initialValue = Resource.Loading())
    {
        value = viewModelteam.getPokemonInfo(currentAktivteam.value.pokemonThree)
    }.value

    val pokemonOne = createPokemon(pokemonInfoPokemonOne)
    val pokemonTwo = createPokemon(pokemonInfoPokemonTwo)
    val pokemonThree = createPokemon(pokemonInfoPokemonThree)

     */

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
                .width(110.dp)
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            PokemonCardTeamUbersichtPokemonOne(navController, battleTeams.pokemonOne, battleTeams)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(110.dp)
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            PokemonCardTeamUbersichtPokemonOne(navController, battleTeams.pokemonTwo, battleTeams)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(110.dp)
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            PokemonCardTeamUbersichtPokemonOne(navController, battleTeams.pokemonThree, battleTeams)
        }
    }
}