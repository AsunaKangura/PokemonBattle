package com.klimpel.abschlussarbeitmodul3.ui.components


import android.content.Context
import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonTeamCard
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.pokedexscreen.Searchbar
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.pokemondetailscreen.PokemonTypeSection
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.AvailablePokemonRow
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.AvailablePokemonRowEditTeam
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.Resource
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.util.parsePokemonNameToGerman
import com.klimpel.abschlussarbeitmodul3.viewmodels.MeinePokemonViewModel
import com.klimpel.abschlussarbeitmodul3.viewmodels.PokemonDetailViewModel
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel
import com.klimpel.pokemonbattlefinal.R


@Composable
fun GradientButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    textcolor: Color = LightBlue,
    gradient: Brush = Brush.linearGradient(listOf(Color.White, Color.White)),
    cornerRadius: Dp = 0.dp,
    border: Dp = 2.dp,
    bordercolor: Color = LightBlue,
    paddingx: Dp = 20.dp,
    paddingy: Dp = 8.dp,
    fontSize: TextUnit = 16.sp,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp,
        bottomStart = 20.dp,
        bottomEnd = 20.dp
    )
) {
    Surface(
        modifier = modifier,
        color = Color.Transparent,
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(cornerRadius),
            onClick = { onClick() }
        ) {
            Box(
                modifier = Modifier
                    .border(border, bordercolor, roundedCornerShape)
                    .background(
                        brush = gradient,
                        shape = roundedCornerShape
                    )
                    .padding(horizontal = paddingx, vertical = paddingy)
                    .clip(roundedCornerShape),
                contentAlignment = Alignment.Center

            ) {
                Text(text = text, color = textcolor, fontSize = fontSize)
            }
        }
    }

}
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamCard2(
    teamId: String,
    teamname: String,
    viewModelteam: TeamViewModel = hiltViewModel()
) {

    Card(
        onClick = {
            viewModelteam.deleteCurrentTeam()
            viewModelteam.getTeamInfo(teamId)
        },
        modifier = Modifier
            .width(calcDp(percentage = 0.3f, dimension = Dimension.Width))
            .height(calcDp(percentage = 0.05f, dimension = Dimension.Height)),
        shape = RoundedCornerShape(
            topStart = 25.dp,
            topEnd = 10.dp,
            bottomStart = 10.dp,
            bottomEnd = 25.dp
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        ),
        border = BorderStroke(1.dp, LightBlue)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = teamname,
                fontSize = 12.sp,
                color = LightBlue,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

 */

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamCard(
    pokemonTeamCard: PokemonTeamCard,
    viewModelteam: TeamViewModel = hiltViewModel()
) {

   var bordercolor = if (pokemonTeamCard.isClicked){
        Color.Green
    }else{
        LightBlue
    }
    Log.e("POKEMON_TEAM_CARD_BEFOR_CLICK", "${pokemonTeamCard.isClicked} / ${pokemonTeamCard.teamName}")
    Card(
        onClick = {
            viewModelteam.deleteCurrentTeam()
            viewModelteam.getTeamInfo(pokemonTeamCard.teamId)
            pokemonTeamCard.isClicked = true
            Log.e("POKEMON_TEAM_CARD_AFTER_CLICK", "${pokemonTeamCard.isClicked}")
        },
        modifier = Modifier
            .width(calcDp(percentage = 0.3f, dimension = Dimension.Width))
            .height(calcDp(percentage = 0.05f, dimension = Dimension.Height)),
        shape = RoundedCornerShape(
            topStart = 25.dp,
            topEnd = 10.dp,
            bottomStart = 10.dp,
            bottomEnd = 25.dp
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        ),
        border = BorderStroke(1.dp, bordercolor)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = pokemonTeamCard.teamName,
                fontSize = 12.sp,
                color = LightBlue,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamCardAdd(
    onClick: () -> Unit,
    viewModelteam: TeamViewModel = hiltViewModel()
) {
    //viewModelteam.deleteCurrentTeam()
    Card(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .width(calcDp(percentage = 0.3f, dimension = Dimension.Width))
            .height(calcDp(percentage = 0.05f, dimension = Dimension.Height)),
        shape = RoundedCornerShape(
            topStart = 25.dp,
            topEnd = 10.dp,
            bottomStart = 10.dp,
            bottomEnd = 25.dp
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        ),
        border = BorderStroke(1.dp, LightBlue)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "AddTeam",
                tint = LightBlue
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTeamCardAddBearbeiten(
    navController: NavController,
    context: Context,
    clickeid: Int,
    viewModel: MeinePokemonViewModel = hiltViewModel(),
    viewModelteam: TeamViewModel = hiltViewModel()
) {
    var clickedID = clickeid
    val pokemonList = viewModel.pokemonUbersicht.collectAsState()
    val openPokemonAddDialog = remember { mutableStateOf(false) }
    if (openPokemonAddDialog.value) {
        Dialog(
            onDismissRequest = { openPokemonAddDialog.value = false },
            properties = DialogProperties(usePlatformDefaultWidth = false),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(
                        calcDp(
                            percentage = 0.8f,
                            dimension = Dimension.Height
                        )
                    ),
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 20.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 50.dp
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 10.dp
                ),
            ) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val (titel, listViewPokemon, btnsave) = createRefs()
                    // Profil Titel
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                        border = BorderStroke(
                            4.dp, LightBlue
                        ),
                        modifier = Modifier
                            .width(180.dp)
                            .height(50.dp)
                            .constrainAs(titel) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            },
                        shape = RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 0.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 50.dp
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.titelpokemonchoice),
                                color = LightBlue,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f)
                            .padding(horizontal = 20.dp)
                            .constrainAs(listViewPokemon) {
                                top.linkTo(titel.bottom, 20.dp)
                                //centerVerticallyTo(parent)
                            }
                    ) {

                        Column (
                            modifier = Modifier
                                .fillMaxSize()
                                .background(DeepRed)
                        ){

                            /*
                            Searchbar(hint = stringResource(R.string.searchbarHint)){
                                viewModel.searchPokemonList(it)
                            }

                             */

                            Spacer(modifier = Modifier.height(20.dp))

                            LazyVerticalGrid(
                                columns = GridCells.Adaptive(130.dp),
                            ) {
                                viewModel.loadOwnedPokemon()
                                val itemCount = pokemonList.value.size
                                items(itemCount) {
                                    AvailablePokemonRowEditTeam(
                                        navController = navController,
                                        context = context,
                                        clickedID = clickedID,
                                        rowIndex = it,
                                        entries = pokemonList.value
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    Card(
        onClick = {
            openPokemonAddDialog.value = true
            clickedID = clickeid
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(calcDp(percentage = 0.15f, dimension = Dimension.Height))
            .padding(4.dp),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(
            topStart = 50.dp,
            topEnd = 20.dp,
            bottomStart = 20.dp,
            bottomEnd = 50.dp
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        ),
        border = BorderStroke(2.dp, LightBlue)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add",
                modifier = Modifier
                    .size(80.dp),
                tint = LightBlue
            )
        }
    }
    Spacer(modifier = Modifier.height(40.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTeamCardAddErstellen(
    navController: NavController,
    context: Context,
    clickeid: Int,
    viewModel: MeinePokemonViewModel = hiltViewModel(),
    viewModelteam: TeamViewModel = hiltViewModel()
) {
    var clickedID = clickeid
    val pokemonList = viewModel.pokemonUbersicht.collectAsState()
    val openPokemonAddDialog = remember { mutableStateOf(false) }
    if (openPokemonAddDialog.value) {
        Dialog(
            onDismissRequest = { openPokemonAddDialog.value = false },
            properties = DialogProperties(usePlatformDefaultWidth = false),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(calcDp(percentage = 0.8f, dimension = Dimension.Height)),
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(topStart = 50.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 50.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
            ) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val (titel, listViewPokemon, btnsave) = createRefs()
                    // Profil Titel

                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White,),
                        border = BorderStroke(4.dp, LightBlue),
                        modifier = Modifier.width(180.dp).height(50.dp)
                            .constrainAs(titel) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            },
                        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 50.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.titelpokemonchoice),
                                color = LightBlue,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }

                    //TitelCard(titel = R.string.titelpokemonchoice)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f)
                            .padding(horizontal = 20.dp)
                            .constrainAs(listViewPokemon) {
                                top.linkTo(titel.bottom, 20.dp)
                            }
                    ) {

                        Column (
                            modifier = Modifier
                                .fillMaxSize()
                                .background(DeepRed)
                        ){

                            /*
                            Searchbar(hint = stringResource(R.string.searchbarHint)){
                                viewModel.searchPokemonList(it)
                            }

                             */

                            Spacer(modifier = Modifier.height(20.dp))

                            LazyVerticalGrid(
                                columns = GridCells.Adaptive(130.dp),
                            ) {
                                viewModel.loadOwnedPokemon()
                                val itemCount = pokemonList.value.size
                                items(itemCount) {
                                    AvailablePokemonRow(
                                        navController = navController,
                                        context = context,
                                        clickedID = clickedID,
                                        rowIndex = it,
                                        entries = pokemonList.value
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    Card(
        onClick = {
            openPokemonAddDialog.value = true
            clickedID = clickeid
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(calcDp(percentage = 0.15f, dimension = Dimension.Height))
            .padding(4.dp),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(
            topStart = 50.dp,
            topEnd = 20.dp,
            bottomStart = 20.dp,
            bottomEnd = 50.dp
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        ),
        border = BorderStroke(2.dp, LightBlue)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add",
                modifier = Modifier
                    .size(80.dp),
                tint = LightBlue
            )
        }
    }
    Spacer(modifier = Modifier.height(40.dp))
}


@Composable
fun PokemonTeamCard(
    navController: NavController,
    pokemonName: String,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
) {
    val pokemonInfo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading())
    {
        value = viewModel.getPokemonInfo(pokemonName)
    }.value

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(calcDp(percentage = 0.15f, dimension = Dimension.Height))
            .padding(4.dp),
        shape = RoundedCornerShape(
            topStart = 50.dp,
            topEnd = 20.dp,
            bottomStart = 20.dp,
            bottomEnd = 50.dp
        ),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        ),
        border = BorderStroke(2.dp, LightBlue)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .fillMaxHeight()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (pokemonInfo is Resource.Success) {
                        pokemonInfo.data?.sprites?.let {
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
            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight(0.8f)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    pokemonInfo.data?.let {
                        Text(
                            text = parsePokemonNameToGerman(it.name),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 20.dp)
                        )
                    }
                    pokemonInfo.data?.let { PokemonTypeSection(types = it.types) }
                }
            }

        }
    }
}

@Composable
fun CardWithAnimatedBorder(
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit = {},
    borderColors: List<Color> = emptyList(),
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val angle by
    infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec =
        infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val brush =
        if (borderColors.isNotEmpty()) Brush.sweepGradient(borderColors)
        else Brush.sweepGradient(listOf(Color.Gray, Color.White))

    Surface(modifier = modifier.clickable { onCardClick() }, shape = RoundedCornerShape(20.dp)) {
        Surface(
            modifier =
            Modifier
                .clipToBounds()
                .fillMaxWidth()
                .padding(4.dp)
                .drawWithContent {
                    rotate(angle) {
                        drawCircle(
                            brush = brush,
                            radius = size.width,
                            blendMode = BlendMode.SrcIn,
                        )
                    }
                    drawContent()
                },
            color = LightBlueBackground,
            shape = RoundedCornerShape(
                topStart = 50.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 50.dp
            )
        ) {
            Box(modifier = Modifier.padding(0.dp)) { content() }
        }
    }
}

@Composable
fun CardWithAnimatedBorderTeam(
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit = {},
    borderColors: List<Color> = emptyList(),
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val angle by
    infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec =
        infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val brush =
        if (borderColors.isNotEmpty()) Brush.sweepGradient(borderColors)
        else Brush.sweepGradient(listOf(Color.Gray, Color.White))

    Surface(modifier = modifier.clickable { onCardClick() }.shadow(10.dp, shape = RectangleShape), shape = RoundedCornerShape(10.dp)) {
        Surface(
            modifier = Modifier
                .clipToBounds()
                .fillMaxWidth()
                .padding(2.dp)
                .drawWithContent {
                    rotate(angle) {
                        drawCircle(
                            brush = brush,
                            radius = size.width,
                            blendMode = BlendMode.SrcIn,
                        )
                    }
                    drawContent()
                },
            color = Color.Transparent,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.padding(2.dp)) { content() }
        }
    }
}


@Composable
fun CardWithAnimatedBorderOwnedPokemon(
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit = {},
    borderColors: List<Color> = emptyList(),
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val angle by
    infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec =
        infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val brush =
        if (borderColors.isNotEmpty()) Brush.sweepGradient(borderColors)
        else Brush.sweepGradient(listOf(Color.Gray, Color.White))

    Surface(modifier = modifier.clickable { onCardClick() }.shadow(10.dp, shape = RectangleShape), shape = RoundedCornerShape(20.dp)) {
        Surface(
            modifier =
            Modifier
                .clipToBounds()
                .fillMaxWidth()
                .padding(4.dp)
                .drawWithContent {
                    rotate(angle) {
                        drawCircle(
                            brush = brush,
                            radius = size.width,
                            blendMode = BlendMode.SrcIn,
                        )
                    }
                    drawContent()
                },
            color = LightBlueBackground,
            shape = RoundedCornerShape(20.dp)
        ) {
            Box(modifier = Modifier.padding(0.dp)) { content() }
        }
    }
}
