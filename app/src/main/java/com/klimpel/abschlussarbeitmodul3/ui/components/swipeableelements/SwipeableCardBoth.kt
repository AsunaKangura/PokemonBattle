package com.klimpel.abschlussarbeitmodul3.ui.components.swipeableelements

import android.content.Context
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ssjetpackcomposeswipeableview.SwipeAbleItemView
import com.example.ssjetpackcomposeswipeableview.SwipeDirection
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeFire
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel


/**
 * Eine Composable-Funktion, die eine swipebare Karte mit beiden Richtungen erstellt.
 *
 * @param context Der Kontext der Anwendung.
 * @param id Die eindeutige ID der Custom Teams.
 * @param navController Der NavController für die Navigation.
 * @param viewModelTeam Das TeamViewModel für die Teamverwaltung.
 * @param team Das BattleTeams-Objekt.
 * @param pokemon Der Name des Pokemon.
 * @param content Der Inhalt der Karte.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableCardBoth(
    context: Context,
    id: Int,
    navController: NavController,
    viewModelTeam: TeamViewModel = hiltViewModel(),
    team: BattleTeams,
    pokemon: String,
    content: @Composable () -> Unit
) {
    SwipeAbleItemView(
        leftViewIcons = arrayListOf(Triple(rememberVectorPainter(image = Icons.Filled.Delete), Color.White, "btnDeleteLeft")),
        rightViewIcons = arrayListOf(Triple((rememberVectorPainter(image = Icons.Filled.Search)), Color.White, "btnDetailRight")),
        position = 0,
        swipeDirection = SwipeDirection.BOTH,
        onClick = { clickInfo ->
            val route = "PokemonDetailScreen/${pokemon}"
            if (clickInfo.second == "btnDetailRight") {
                navController.navigate(route)
            }
            if (clickInfo.second == "btnDeleteLeft"){
               // viewModelTeam.deldeletePokemoninTeam(context, id, team.teamName)
            }
        },
        leftViewWidth = calcDp(percentage = 0.3f, dimension = Dimension.Width),
        rightViewWidth = calcDp(percentage = 0.3f, dimension = Dimension.Width),
        height = calcDp(percentage = 0.15f, dimension = Dimension.Height),
        leftViewBackgroundColor = DeepRed,
        rightViewBackgroundColor = LightBlue,
        cornerRadius = 20.dp,
        leftSpace = (-30).dp,
        rightSpace = (-30).dp,
        fractionalThreshold = 0f
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableCardBothStore(
    navController: NavController,
    content: @Composable () -> Unit
) {
    SwipeAbleItemView(
        leftViewIcons = arrayListOf(Triple(rememberVectorPainter(image = Icons.Filled.ShoppingCart), Color.White, "btnDeleteLeft")),
        rightViewIcons = arrayListOf(Triple((rememberVectorPainter(image = Icons.Filled.Search)), Color.White, "btnDetailRight")),
        position = 0,
        swipeDirection = SwipeDirection.BOTH,
        onClick = { clickInfo ->
            val route = "PokemonDetailScreen/"
            if (clickInfo.second == "btnDetailRight") {
                navController.navigate(route)
            }
            if (clickInfo.second == "btnDeleteLeft"){

            }
        },
        leftViewWidth = 10.dp,
        rightViewWidth = 10.dp,
        height = 30.dp,
        leftViewBackgroundColor = TypeFire,
        rightViewBackgroundColor = LightBlue,
        cornerRadius = 20.dp,
        leftSpace = 0.dp,
        rightSpace = 0.dp,
        fractionalThreshold = 1f
    ) {
        content()
    }
}