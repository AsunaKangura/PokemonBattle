package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.pokedexscreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ssjetpackcomposeswipeableview.SwipeAbleItemView
import com.example.ssjetpackcomposeswipeableview.SwipeDirection
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.data.models.PokedexListEntry
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.util.parsePokemonNameToGerman
import com.klimpel.abschlussarbeitmodul3.viewmodels.PokemonListViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonCard(
    entry: PokedexListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel(),
) {
    val swipeDirection: SwipeDirection? = null

    Card(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(calcDp(percentage = 0.30f, dimension = Dimension.Width)),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        ),
        border = BorderStroke(2.dp, Color.White),
        shape = RoundedCornerShape(
            topStart = 50.dp,
            topEnd = 20.dp,
            bottomStart = 20.dp,
            bottomEnd = 50.dp
        )
    ) {

        Card(
            onClick = { },
            modifier = Modifier
                .fillMaxSize(),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 10.dp
            ),
            border = BorderStroke(2.dp, Color.White),
            shape = RoundedCornerShape(
                topStart = 50.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 50.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.5f)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = LightBlue,
                        ),
                        border = BorderStroke(
                            4.dp, LightBlue
                        ),
                        modifier = Modifier
                            .width(200.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 0.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 50.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = parsePokemonNameToGerman(entry.pokemonName.lowercase()),
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                            )
                            Log.e(
                                "POKELISTSCREEN",
                                "${parsePokemonNameToGerman(entry.pokemonName.lowercase())}"
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = entry.imageUrl,
                        contentDescription = entry.pokemonName,
                        modifier = Modifier.size(140.dp)
                    )
                }
            }
        }
    }
}