package com.klimpel.abschlussarbeitmodul3.ui.components.swipeableelements

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.ssjetpackcomposeswipeableview.SwipeAbleItemView
import com.example.ssjetpackcomposeswipeableview.SwipeDirection
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableCardright(
    navController: NavController,
    content: @Composable () -> Unit
){
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
                "btnDetailRight"
            )
        ),
        // Position of the item normally required only when used with lazyColumns to identify the index of the item.
        position = 0,
        // Swipe direction it can be Left or Right or Both (Left + Right).
        swipeDirection = SwipeDirection.RIGHT,
        // Perform any action when swipeable view is clicked. It provides the position(Index of the item) as well as id to identify which item clicked incase of multiple items.
        onClick = { // Pair(Position, Id)
            if (it.second == "btnDeleteLeft") {
            }
        },
        // Width for the left side of the view which will be shown when swiped.
        //leftViewWidth = calcDp(percentage = 0.25f, dimension = Dimension.Width),
        // Width for the right side of the view which will be shown when swiped.
        rightViewWidth = calcDp(percentage = 0.25f, dimension = Dimension.Width),
        // Height for the swipeable view.
        height = calcDp(percentage = 0.15f, dimension = Dimension.Height),
        // Background color for left view.
        leftViewBackgroundColor = LightBlue,
        // Background color for right view.
        rightViewBackgroundColor = LightBlue,
        // Corner radius for swipeable view.
        cornerRadius = 20.dp,
        // Space between left swipeable view and your main content view.
        leftSpace = 10.dp,
        // Space between right swipeable view and your main content view.
        rightSpace = 10.dp,
        // fractionalThreshold for the swipe
        fractionalThreshold = 0.4f
    ) {
        content()
    }
}