package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.store

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.White
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.StoreViewModel

@Composable
fun StoreCategory(
    navController: NavController,
    categoryName: String,
    categoryID: String,
    viewModelStore: StoreViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()

    // Normale und Spezial Pokebälle
    Spacer(modifier = Modifier.height(calcDp(percentage = 0.02f, dimension = Dimension.Height)))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(calcDp(percentage = 0.24f, dimension = Dimension.Height))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.20f)
                .background(Color.Transparent)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = categoryName,
                color = LightBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Divider(thickness = 1.dp, color = LightBlue)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                //.horizontalScroll(scrollState)
        ) {


            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                val count = 5

                item { Spacer(modifier = Modifier.width(0.dp)) }
                items(count) {
                   StoreItem(navController = navController)
                }
                item { Spacer(modifier = Modifier.width(10.dp)) }
            }



        }
    }
    Divider(thickness = 1.dp, color = LightBlue)
    Spacer(modifier = Modifier.height(calcDp(percentage = 0.02f, dimension = Dimension.Height)))
}