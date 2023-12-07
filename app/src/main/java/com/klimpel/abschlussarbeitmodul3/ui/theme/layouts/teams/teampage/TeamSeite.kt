package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.Green
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.White
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen.PokemonCardTeamUbersichtOhneBattleTeam
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen.TeamCardPokemon
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teamerstellen.PokemonTypeItem
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.Resource
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.util.winratecolor
import com.klimpel.abschlussarbeitmodul3.viewmodels.SearchingViewModel
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel
import com.klimpel.pokemonbattlefinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamPage(
    navController: NavController,
    teamName: String,
    viewModelteam: TeamViewModel = hiltViewModel(),
    viewModelSearch: SearchingViewModel = hiltViewModel(),
    topPadding: Dp = 20.dp,
    pokemonImageSize: Dp = 0.dp,
) {

    val ownedPokemonList = viewModelteam.pokemonUbersicht.collectAsState()
    val currentAktivteam = viewModelteam.currentTeam.collectAsState()

    var color = Color.Black
    val winrate = resultWinRate(currentAktivteam.value.games, currentAktivteam.value.wins)
    val loses = resultLoses(currentAktivteam.value.games, currentAktivteam.value.wins)

    // Alle Drei Pokemon aus dem Team
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

    var teamStats: Resource<BattleTeams?>? = null
    if (pokemonOne != null && pokemonTwo != null && pokemonThree != null) {
        teamStats = teamstats(pokemonOne, pokemonTwo, pokemonThree, currentAktivteam.value)
    }

    Scaffold(
        topBar = {
            TopAppBarTitelBackArrowTeamSeite(
                pageTitle = teamName,
                navController = navController
            )
        },
        containerColor = LightBlueBackground,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Divider(thickness = 4.dp, color = LightBlue)
            Spacer(modifier = Modifier.height(30.dp))

            // Team Statistiken
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.22f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight()
                ) {

                    // Team Stats
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.60f)
                    ) {
                        Text(
                            text = "Team Stats",
                            color = LightBlue,
                            fontWeight = FontWeight.Bold
                        )
                        TeamDetailStateWrapper(
                            teamStats = teamStats,
                            modifier = Modifier
                                .fillMaxSize()
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
                    Spacer(modifier = Modifier.width(10.dp))
                    // BattleStatistik
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Battlestatistik",
                            fontWeight = FontWeight.Bold,
                            color = LightBlue,
                        )
                        Box(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 0.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth()
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(0.5f)
                                    ) {
                                        Text(text = "Games:")
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Text(text = "Wins:")
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Text(text = "Loses:")
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Text(text = "WinRate:")
                                    }
                                    Column(
                                        horizontalAlignment = Alignment.End,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight()
                                    ) {
                                        Text(
                                            text = currentAktivteam.value.games.toString(),
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Text(
                                            text = currentAktivteam.value.wins.toString(),
                                            color = Green,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Text(
                                            text = loses.toString(),
                                            color = DeepRed,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Text(
                                            text = "$winrate %",
                                            fontWeight = FontWeight.Bold,
                                            color = winratecolor(winrate)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            // Button Section
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(calcDp(percentage = 0.05f, dimension = Dimension.Height))
            ) {
                Row(
                    modifier = Modifier
                        .width(calcDp(percentage = 0.8f, dimension = Dimension.Width))
                        .fillMaxHeight()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .width(calcDp(percentage = 0.35f, dimension = Dimension.Width))
                                .fillMaxHeight()
                                .shadow(10.dp, shape = CircleShape)
                        ) {
                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(containerColor = LightBlue),

                                ) {
                                Icon(
                                    imageVector = Icons.Outlined.Edit,
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = "bearbeiten", color = Color.White, fontSize = 12.sp)
                            }
                        }
                        Box(
                            modifier = Modifier
                                .width(calcDp(percentage = 0.10f, dimension = Dimension.Width))
                                .fillMaxHeight()
                        )
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .width(calcDp(percentage = 0.35f, dimension = Dimension.Width))
                                .fillMaxHeight()
                                .shadow(10.dp, shape = CircleShape)
                        ) {
                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(containerColor = DeepRed),

                                ) {
                                Icon(
                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = "löschen", color = Color.White, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            // Typen Section
            Spacer(modifier = Modifier.height(20.dp))
            // Team Auftellung
            Divider(thickness = 1.dp, color = LightBlue)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(calcDp(percentage = 0.3f, dimension = Dimension.Height))
                    .background(White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Team Übersicht:",
                        color = LightBlue,
                        fontWeight = FontWeight.Bold
                    )
                    TeamCardPokemon(
                        battleTeams = currentAktivteam.value,
                        navController = navController
                    )
                }
            }
            Divider(thickness = 1.dp, color = LightBlue)
            Spacer(modifier = Modifier.height(20.dp))
        }


    }
}


fun resultLoses(games: Int, wins: Int): Int {
    return games - wins
}

fun resultWinRate(games: Int, wins: Int): Int {
    return (games / 100) * wins
}

fun teamstats(
    pokemon1: Pokemon, pokemon2: Pokemon, pokemon3: Pokemon, battleTeams: BattleTeams
): Resource<BattleTeams?> {
    val response = BattleTeams(
        teamName = battleTeams.teamName,
        pokemonOne = battleTeams.pokemonOne,
        pokemonTwo = battleTeams.pokemonTwo,
        pokemonThree = battleTeams.pokemonThree,
        games = battleTeams.games,
        wins = battleTeams.wins,
        hp = pokemon1.stats[0].basestat + pokemon2.stats[0].basestat + pokemon3.stats[0].basestat,
        atk = pokemon1.stats[1].basestat + pokemon2.stats[1].basestat + pokemon3.stats[1].basestat,
        def = pokemon1.stats[2].basestat + pokemon2.stats[2].basestat + pokemon3.stats[2].basestat,
        spatk = pokemon1.stats[3].basestat + pokemon2.stats[3].basestat + pokemon3.stats[3].basestat,
        spdef = pokemon1.stats[4].basestat + pokemon2.stats[4].basestat + pokemon3.stats[4].basestat,
        spd = pokemon1.stats[5].basestat + pokemon2.stats[5].basestat + pokemon3.stats[5].basestat
    )
    return Resource.Success(response)
}

fun teamstatserstellen(): Resource<BattleTeams?> {
    val response = BattleTeams(
        teamName = "",
        pokemonOne = "",
        pokemonTwo = "",
        pokemonThree = "",
        games = 0,
        wins = 0,
        hp = 0,
        atk = 0,
        def = 0,
        spatk = 0,
        spdef = 0,
        spd = 0,
    )
    return Resource.Success(response)
}

fun createPokemon(resource: Resource<Pokemon>): Pokemon? {
    val pokemonOne = resource.data?.let {
        Pokemon(
            abilities = it.abilities,
            baseexperience = it.baseexperience,
            forms = it.forms,
            game_indices = it.game_indices,
            height = it.height,
            held_items = it.held_items,
            id = it.id,
            is_default = it.is_default,
            location_area_encounters = it.location_area_encounters,
            moves = it.moves,
            name = it.name,
            order = it.order,
            past_types = it.past_types,
            species = it.species,
            sprites = it.sprites,
            stats = it.stats,
            types = it.types,
            weight = it.weight
        )
    }
    return pokemonOne
}

