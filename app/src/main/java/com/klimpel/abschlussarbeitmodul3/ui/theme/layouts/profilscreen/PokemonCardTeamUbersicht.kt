package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.ui.components.CardWithAnimatedBorderTeam
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.util.pokemonEvoloutionBorder
import com.klimpel.abschlussarbeitmodul3.util.Resource
import com.klimpel.abschlussarbeitmodul3.util.backgroundBrush
import com.klimpel.abschlussarbeitmodul3.viewmodels.PokemonDetailViewModel
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonCardTeamUbersichtPokemonOne(
    navController: NavController,
    pokemonname: String,
    battleTeams: BattleTeams,
    viewModelteam: TeamViewModel = hiltViewModel(),
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {

    val pokemonInfo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading())
    {
        value = viewModel.getPokemonInfo(pokemonname)
    }.value

    val scope = rememberCoroutineScope()

    pokemonInfo.data?.name?.let { pokemonEvoloutionBorder(it) }?.let {
        CardWithAnimatedBorderTeam(
            borderColors = it
        ) {
            Card(
                onClick = {
                    viewModelteam.deleteCurrentTeam()
                    viewModelteam.loadTeam(battleTeams.teamName)
                    scope.launch {
                        delay(500)
                        navController.navigate("TeamSeite/${battleTeams.teamName}")
                    }
                },
                modifier = Modifier
                    .size(90.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                ) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val (image, name, type) = createRefs()

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(Brush.horizontalGradient(backgroundBrush(pokemonInfo.data.types)))
                                .constrainAs(image) {
                                    top.linkTo(parent.top)
                                }
                        ) {
                            if (pokemonInfo is Resource.Success) {
                                pokemonInfo.data.sprites.let {
                                    AsyncImage(
                                        model = it.frontDefault,
                                        contentDescription = pokemonInfo.data.name,
                                        modifier = Modifier
                                            .size(100.dp)
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

@Composable
fun PokemonCardTeamUbersichtOhneBattleTeam(
    navController: NavController,
    pokemonname: String,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {

    val pokemonInfo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading())
    {
        value = viewModel.getPokemonInfo(pokemonname)
    }.value

    var test = pokemonInfo.data?.types?.let { backgroundBrush(it) }

    pokemonInfo.data?.name?.let { pokemonEvoloutionBorder(it) }?.let {
        CardWithAnimatedBorderTeam(
            borderColors = it
        ) {
            Card(
                modifier = Modifier
                   .size(90.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 10.dp
                ),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val (image, name) = createRefs()

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(Brush.horizontalGradient(backgroundBrush(pokemonInfo.data.types)))
                                .constrainAs(image) {
                                    top.linkTo(parent.top)
                                }
                        ) {
                            if (pokemonInfo is Resource.Success) {
                                pokemonInfo.data.sprites.let {
                                    AsyncImage(
                                        model = it.frontDefault,
                                        contentDescription = pokemonInfo.data.name,
                                        modifier = Modifier
                                            .size(100.dp)
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

@Composable
fun PokemonCardTeamErstellen(
    navController: NavController,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {

    val test = listOf(LightBlue, LightBlue)

    CardWithAnimatedBorderTeam(
        borderColors = test
    ) {
        Card(
            modifier = Modifier
                .size(90.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 10.dp
            ),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val (image, name) = createRefs()

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(LightBlueBackground)
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                            }
                    ) {
                        Text(text = "Leer")
                    }
                }
            }
        }
    }
}


