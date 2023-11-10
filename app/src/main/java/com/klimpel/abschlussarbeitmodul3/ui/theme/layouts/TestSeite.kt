package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ssjetpackcomposeswipeableview.SwipeAbleItemView
import com.example.ssjetpackcomposeswipeableview.SwipeDirection
import com.klimpel.abschlussarbeitmodul3.ui.theme.HPColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.util.parsePokemonNameToGerman
import com.klimpel.pokemonbattlefinal.R

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun testScreen() {
    val swipeDirection: SwipeDirection? = null

    SwipeAbleItemView(
        // Triplet(Icon, TintColor, Id) Pass the icon with the tint color which you want to display in left side view, Id will be used to identify onClick Events.

        leftViewIcons = arrayListOf(
            Triple(
                rememberVectorPainter(image = Icons.Filled.Delete),
                Color.White,
                "btnDeleteLeft"
            )
        ),
        // Triplet(Icon, TintColor, Id) Pass the icon with the tint color which you want to display in right side view, Id will be used to identify onClick Events.
        rightViewIcons = arrayListOf(
            Triple(
                (rememberVectorPainter(image = Icons.Filled.Search)),
                Color.White,
                "btnDeleteRight"
            )
        ),
        // Position of the item normally required only when used with lazyColumns to identify the index of the item.
        position = 0,
        // Swipe direction it can be Left or Right or Both (Left + Right).
        swipeDirection = swipeDirection ?: SwipeDirection.BOTH,
        // Perform any action when swipeable view is clicked. It provides the position(Index of the item) as well as id to identify which item clicked incase of multiple items.
        onClick = { // Pair(Position, Id)
            if (it.second == "btnDeleteRight"){

            }
        },
        // Width for the left side of the view which will be shown when swiped.
        leftViewWidth = calcDp(percentage = 0.25f, dimension = Dimension.Width),
        // Width for the right side of the view which will be shown when swiped.
        rightViewWidth = calcDp(percentage = 0.25f, dimension = Dimension.Width),
        // Height for the swipeable view.
        height = calcDp(percentage = 0.15f, dimension = Dimension.Height),
        // Background color for left view.
        leftViewBackgroundColor = LightBlue,
        // Background color for right view.
        rightViewBackgroundColor = LightBlue,
        // Corner radius for swipeable view.
        cornerRadius = 4.dp,
        // Space between left swipeable view and your main content view.
        leftSpace = 10.dp,
        // Space between right swipeable view and your main content view.
        rightSpace = 10.dp,
        // fractionalThreshold for the swipe
        fractionalThreshold = 0.4f
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


        }
    }
}

@Composable
fun testObjekt(){

}