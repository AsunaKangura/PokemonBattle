package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teamerstellen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.input.key.Key.Companion.F
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
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
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonGrindEntry
import com.klimpel.abschlussarbeitmodul3.ui.components.EditTextField
import com.klimpel.abschlussarbeitmodul3.ui.components.draganddrop.DragTarget
import com.klimpel.abschlussarbeitmodul3.ui.components.draganddrop.DragTargetInfo
import com.klimpel.abschlussarbeitmodul3.ui.components.draganddrop.DragableScreen
import com.klimpel.abschlussarbeitmodul3.ui.components.draganddrop.DrapAndDropViewModel
import com.klimpel.abschlussarbeitmodul3.ui.components.draganddrop.DropItem
import com.klimpel.abschlussarbeitmodul3.ui.components.draganddrop.LocalDragTargetInfo
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.White
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen.PokemonCardTeamErstellen
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen.PokemonCardTeamUbersichtOhneBattleTeam
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen.TeamCardPokemon
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.TeamDetailStateWrapper
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.createPokemon
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.resultLoses
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.resultWinRate
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.teamstats
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teampage.teamstatserstellen
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.topappbars.TopAppBarTitelBackArrowTeamErstellen
import com.klimpel.abschlussarbeitmodul3.util.Resource
import com.klimpel.abschlussarbeitmodul3.viewmodels.SearchingViewModel
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel
import com.klimpel.pokemonbattlefinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamErstellenScreen2(
    navController: NavController,
    viewModelteam: TeamViewModel = hiltViewModel(),
    viewModelSearch: SearchingViewModel = hiltViewModel(),
    viewModeldraganddrop: DrapAndDropViewModel = hiltViewModel(),
    topPadding: Dp = 20.dp,
    pokemonImageSize: Dp = 0.dp,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val ownedPokemonList = viewModelteam.pokemonUbersicht.collectAsState()
    val currentAktivteam = viewModelteam.currentTeam.collectAsState()

    val pokemonTeamList: MutableList<String> = mutableListOf()
    val teamStats: Resource<BattleTeams?> = teamstatserstellen()

    var pokemonInfoPokemonOne: Resource<Pokemon>? = null
    var pokemonInfoPokemonTwo: Resource<Pokemon>? = null
    var pokemonInfoPokemonThree: Resource<Pokemon>? = null

    // pokemon 1 bis 3 leer
    if (
        (currentAktivteam.value.pokemonOne != "" && currentAktivteam.value.pokemonTwo != "" && currentAktivteam.value.pokemonThree != "") ||
        (currentAktivteam.value.pokemonOne != "" && currentAktivteam.value.pokemonTwo != "" && currentAktivteam.value.pokemonThree == "") ||
        (currentAktivteam.value.pokemonOne != "" && currentAktivteam.value.pokemonTwo == "" && currentAktivteam.value.pokemonThree != "") ||
        (currentAktivteam.value.pokemonOne == "" && currentAktivteam.value.pokemonTwo != "" && currentAktivteam.value.pokemonThree != "")
    ) {
        // Alle Drei Pokemon aus dem Team
        pokemonInfoPokemonOne =
            produceState<Resource<Pokemon>>(initialValue = Resource.Loading())
            {
                value = viewModelteam.getPokemonInfo(currentAktivteam.value.pokemonOne ?: "")
            }.value
        pokemonInfoPokemonTwo =
            produceState<Resource<Pokemon>>(initialValue = Resource.Loading())
            {
                value = viewModelteam.getPokemonInfo(currentAktivteam.value.pokemonTwo ?: "")
            }.value
        pokemonInfoPokemonThree =
            produceState<Resource<Pokemon>>(initialValue = Resource.Loading())
            {
                value = viewModelteam.getPokemonInfo(currentAktivteam.value.pokemonThree ?: "")
            }.value


        val pokemonOne = createPokemon(pokemonInfoPokemonOne)
        val pokemonTwo = createPokemon(pokemonInfoPokemonTwo)
        val pokemonThree = createPokemon(pokemonInfoPokemonThree)

        if (pokemonOne != null && pokemonTwo != null && pokemonThree != null) {
            teamstats(pokemonOne, pokemonTwo, pokemonThree, currentAktivteam.value)
        }
    }

    AbschlussarbeitModul3Theme {
        Scaffold(
            topBar = {
                TopAppBarTitelBackArrowTeamErstellen(
                    pageTitle = R.string.titelTeamErstellenScreen,
                    navController = navController
                )
            },
            containerColor = LightBlueBackground,
        ) { padding ->
            DragableScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Divider(thickness = 4.dp, color = LightBlue)
                    Spacer(modifier = Modifier.height(5.dp))

                    // Team Statistiken
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(0.20f)
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
                                        .padding(16.dp),
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
                            // Typen
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Typen",
                                    fontWeight = FontWeight.Bold,
                                    color = LightBlue,
                                )

                                if (pokemonInfoPokemonOne?.data != null && pokemonInfoPokemonTwo?.data != null && pokemonInfoPokemonThree?.data != null) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .height(35.dp)
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            pokemonInfoPokemonOne?.data?.name?.let { Text(text = it) }
                                            pokemonInfoPokemonOne?.data?.types?.let {
                                                PokemonTypeItem(
                                                    types = it
                                                )
                                            }
                                        }
                                    }
                                } else {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        Text(
                                            text = "Keine Typen",
                                            color = LightBlue,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "vorhanden",
                                            color = LightBlue,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    // Team Auftellung
                    Divider(thickness = 1.dp, color = LightBlue)
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.3f)
                            .background(White)
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .fillMaxHeight(0.2f)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxWidth(0.35f)
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = "Aufstellung:",
                                    color = LightBlue,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.End,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            ) {
                                Button(
                                    onClick = { /*TODO*/ },
                                    colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                                    enabled = false
                                ) {
                                    Text(text = "Speichern", fontSize = 10.sp)
                                }
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .fillMaxHeight()
                                .background(DeepRed)
                        ) {

                        }
                    }
                    Divider(thickness = 1.dp, color = LightBlue)


                    // Verfügbare PokemonSection
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))

                        AnimatedVisibility(
                            viewModeldraganddrop.isCurrentlyDragging,
                            enter = slideInHorizontally(initialOffsetX = { it })
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .fillMaxHeight(0.2f)
                                    .background(Color.Cyan)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .width(90.dp)
                                        .fillMaxHeight()
                                ) {
                                    DropItem<PokemonGrindEntry>(
                                        modifier = Modifier
                                            .size(90.dp)
                                    ) { isInBound, personItem ->
                                        if (personItem != null) {
                                            LaunchedEffect(key1 = personItem) {

                                            }
                                        }
                                        if (isInBound) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .border(
                                                        1.dp,
                                                        color = Color.Red,
                                                        shape = RoundedCornerShape(15.dp)
                                                    )
                                                    .background(
                                                        Color.Gray.copy(0.5f),
                                                        RoundedCornerShape(15.dp)
                                                    ),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "Pokemon 1",
                                                    color = Color.Black,
                                                    fontSize = 12.sp
                                                )
                                            }
                                        } else {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .border(
                                                        1.dp,
                                                        color = Color.White,
                                                        shape = RoundedCornerShape(15.dp)
                                                    )
                                                    .background(
                                                        Color.Black.copy(0.5f),
                                                        RoundedCornerShape(15.dp)
                                                    ),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "Pokemon 1",
                                                    color = Color.White,
                                                    fontSize = 12.sp
                                                )
                                            }
                                        }
                                    }
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .width(90.dp)
                                        .fillMaxHeight()
                                ) {
                                    DropItem<PokemonGrindEntry>(
                                        modifier = Modifier
                                            .size(90.dp)
                                    ) { isInBound, personItem ->
                                        if (personItem != null) {
                                            LaunchedEffect(key1 = personItem) {

                                            }
                                        }
                                        if (isInBound) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .border(
                                                        1.dp,
                                                        color = Color.Red,
                                                        shape = RoundedCornerShape(15.dp)
                                                    )
                                                    .background(
                                                        Color.Gray.copy(0.5f),
                                                        RoundedCornerShape(15.dp)
                                                    ),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "Pokemon 2",
                                                    color = Color.Black,
                                                    fontSize = 12.sp
                                                )
                                            }
                                        } else {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .border(
                                                        1.dp,
                                                        color = Color.White,
                                                        shape = RoundedCornerShape(15.dp)
                                                    )
                                                    .background(
                                                        Color.Black.copy(0.5f),
                                                        RoundedCornerShape(15.dp)
                                                    ),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "Pokemon 2",
                                                    color = Color.White,
                                                    fontSize = 12.sp
                                                )
                                            }
                                        }
                                    }
                                }
                                Column(
                                    modifier = Modifier
                                        .width(90.dp)
                                        .fillMaxHeight()
                                ) {

                                    DropItem<PokemonGrindEntry>(
                                        modifier = Modifier
                                            .size(90.dp)
                                    ) { isInBound, personItem ->
                                        if (personItem != null) {
                                            LaunchedEffect(key1 = personItem) {
                                            }
                                        }
                                        if (isInBound) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .border(
                                                        1.dp,
                                                        color = Color.Red,
                                                        shape = RoundedCornerShape(15.dp)
                                                    )
                                                    .background(
                                                        Color.Gray.copy(0.5f),
                                                        RoundedCornerShape(15.dp)
                                                    ),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "Pokemon 3",
                                                    color = Color.Black,
                                                    fontSize = 12.sp
                                                )
                                            }
                                        } else {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .border(
                                                        1.dp,
                                                        color = Color.White,
                                                        shape = RoundedCornerShape(15.dp)
                                                    )
                                                    .background(
                                                        Color.Black.copy(0.5f),
                                                        RoundedCornerShape(15.dp)
                                                    ),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "Pokemon 3",
                                                    color = Color.White,
                                                    fontSize = 12.sp
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }


                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .fillMaxHeight()
                        ) {
                            val searchText by viewModelSearch.searchText.collectAsState()
                            val ownedPokemon by viewModelSearch.ownedPokemon.collectAsState()
                            val isSearching by viewModelSearch.isSearching.collectAsState()
                            var isHintDisplayed by remember {
                                mutableStateOf(false)
                            }

                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 0.dp)
                            ) {
                                BasicTextField(
                                    value = searchText,
                                    onValueChange = viewModelSearch::onSearchTextChange,
                                    maxLines = 1,
                                    singleLine = true,
                                    textStyle = TextStyle(Color.Black),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .shadow(
                                            5.dp,
                                            RoundedCornerShape(20.dp)
                                        )
                                        .background(Color.White)
                                        .padding(horizontal = 20.dp, vertical = 12.dp)
                                        .onFocusChanged {
                                            isHintDisplayed = !isHintDisplayed
                                        }
                                )
                                if (isHintDisplayed) {
                                    Text(
                                        text = "Nach Pokemonname,PokemonID, PokemonType suchen",
                                        color = LightBlue,
                                        modifier = Modifier.padding(
                                            horizontal = 20.dp,
                                            vertical = 12.dp
                                        ),
                                        fontSize = 12.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = stringResource(id = R.string.dropmenuitemmeinepokemon),
                                color = LightBlue,
                                fontWeight = FontWeight.Bold,
                            )

                            Divider(thickness = 1.dp, color = LightBlue)

                            Spacer(modifier = Modifier.height(20.dp))

                            if (isSearching) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            } else {

                                LazyVerticalGrid(
                                    columns = GridCells.Adaptive(80.dp),
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .fillMaxHeight(0.9f),
                                    verticalArrangement = Arrangement.spacedBy(20.dp),
                                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                                ) {
                                    val itemcount = ownedPokemon.size

                                    items(itemcount) {

                                            DragTarget(
                                                dataToDrop = ownedPokemon[it]
                                            ) {
                                                PokemonCardTeamUbersichtOhneBattleTeam(
                                                    navController = navController,
                                                    pokemonname = ownedPokemon[it].name
                                                )
                                            }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
