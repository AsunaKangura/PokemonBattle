package com.klimpel.abschlussarbeitmodul3.ui.components.swipeableelements

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ssjetpackcomposeswipeableview.SwipeAbleItemView
import com.example.ssjetpackcomposeswipeableview.SwipeDirection
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp


/**
 * Eine Composable-Funktion, die eine swipebare Karte mit Linksrichtung erstellt.
 *
 * @param navController Der NavController für die Navigation.
 * @param route Die Route für die Navigation.
 * @param content Der Inhalt der Karte.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableCardLeft(
    navController: NavController,
    route: String,
    content: @Composable () -> Unit
) {
    SwipeAbleItemView(
        leftViewIcons = arrayListOf(
            Triple(
                rememberVectorPainter(image = Icons.Filled.Delete),
                Color.White,
                "btnDeleteLeft"
            )
        ),
        rightViewIcons = arrayListOf(
            Triple(
                rememberVectorPainter(image = Icons.Filled.Search),
                Color.White,
                "btnDetailRight"
            )
        ),
        position = 0,
        swipeDirection = SwipeDirection.LEFT,
        onClick = { clickInfo ->
            if (clickInfo.second == "btnDetailRight") {
                navController.navigate(route)
            }
        },
        rightViewWidth = calcDp(percentage = 0.30f, dimension = Dimension.Width),
        height = calcDp(percentage = 0.15f, dimension = Dimension.Height),
        leftViewBackgroundColor = LightBlue,
        rightViewBackgroundColor = LightBlue,
        cornerRadius = 20.dp,
        leftSpace = (-30).dp,
        rightSpace = (-30).dp,
        fractionalThreshold = 0.4f
    ) {
        content()
    }
}