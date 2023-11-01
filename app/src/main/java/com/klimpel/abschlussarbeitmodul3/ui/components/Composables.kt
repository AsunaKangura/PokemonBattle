package com.klimpel.abschlussarbeitmodul3.ui.components


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.pokemondetailscreen.PokemonTypeSection
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.Resource
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.util.parsePokemonNameToGerman
import com.klimpel.abschlussarbeitmodul3.viewmodels.PokemonDetailViewModel
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel
import java.util.Collections.rotate


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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun teamCard(
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun teamCardAdd(
    onClick: () -> Unit,
    viewModelteam: TeamViewModel = hiltViewModel()
) {
    Card(
        onClick = { onClick() },
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
fun PokemonTeamCard(
    navController: NavController,
    pokemonName: String,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemonInfo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading())
    {
        value = viewModel.getPokemonInfo(pokemonName)
    }.value

    Card(
        onClick = { navController.navigate("PokemonDetailScreen/${pokemonName}") },
        modifier = Modifier
            .fillMaxWidth()
            .height(calcDp(percentage = 0.15f, dimension = Dimension.Height)),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTeamCardAdd(
    navController: NavController,
) {

    Card(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(calcDp(percentage = 0.15f, dimension = Dimension.Height)),
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
                    .size(80.dp)

            )
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
            Modifier.clipToBounds().fillMaxWidth().padding(4.dp).drawWithContent {
                rotate(angle) {
                    drawCircle(
                        brush = brush,
                        radius = size.width,
                        blendMode = BlendMode.SrcIn,
                    )
                }
                drawContent()
            },
            color = LightBlue,
            shape = RoundedCornerShape(19.dp)
        ) {
            Box(modifier = Modifier.padding(0.dp)) { content() }
        }
    }
}

@Preview
@Composable
fun test() {
    CardWithAnimatedBorder(content = {
        Text(
        text = "Gen 1",
        fontFamily = pokemonFontFamily,
        fontSize = 40.sp,
        color = Color.White,
    )},
        borderColors = listOf(
            Color(0xFFFF595A),
            Color(0xFFFFC766),
            Color(0xFF35A07F),
            Color(0xFF35A07F),
            Color(0xFFFFC766),
            Color(0xFFFF595A)
        )

    )
}