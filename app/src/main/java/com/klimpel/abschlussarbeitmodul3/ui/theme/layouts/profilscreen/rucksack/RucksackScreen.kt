package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen.rucksack

import androidx.compose.foundation.background
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.ui.components.TopAppBarTitelBackArrow
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.White
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.pokemonbattlefinal.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RucksackScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { TopAppBarTitelBackArrow(R.string.titelRucksack, navController) },
        containerColor = LightBlueBackground,
    ) {innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .verticalScroll(scrollState)
        ) {
            Divider(thickness = 4.dp, color = LightBlue)

            // Normale und Spezial Pokebälle
            Spacer(modifier = Modifier.height(calcDp(percentage = 0.02f, dimension = Dimension.Height)))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(calcDp(percentage = 0.24f, dimension = Dimension.Height))
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.20f)
                ) {
                    Text(text = "Pokebälle", color = LightBlue, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "Anzahl: 0", color = LightBlue, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
                Divider(thickness = 1.dp, color = LightBlue)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(White)
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ){
                        item { Spacer(modifier = Modifier.width(10.dp)) }
                        items(5){
                            RucksackListItem()
                        }
                        item { Spacer(modifier = Modifier.width(10.dp)) }
                    }
                }
            }
            Divider(thickness = 1.dp, color = LightBlue)

            // Element Pokebälle
            Spacer(modifier = Modifier.height(calcDp(percentage = 0.02f, dimension = Dimension.Height)))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(calcDp(percentage = 0.24f, dimension = Dimension.Height))
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.2f)
                ) {
                    Text(text = "Element Pokebälle", color = LightBlue, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "Anzahl: 0", color = LightBlue, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
                Divider(thickness = 1.dp, color = LightBlue)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(White)
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ){
                        item { Spacer(modifier = Modifier.width(10.dp)) }
                        items(5){
                            RucksackListItem()
                        }
                        item { Spacer(modifier = Modifier.width(10.dp)) }
                    }
                }
            }
            Divider(thickness = 1.dp, color = LightBlue)

            // Useabels Items
            Spacer(modifier = Modifier.height(calcDp(percentage = 0.02f, dimension = Dimension.Height)))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(calcDp(percentage = 0.24f, dimension = Dimension.Height))
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.2f)
                ) {
                    Text(text = "Useabel Items", color = LightBlue, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "Anzahl: 0", color = LightBlue, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
                Divider(thickness = 1.dp, color = LightBlue)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(White)
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ){
                        item { Spacer(modifier = Modifier.width(10.dp)) }
                        items(5){
                            RucksackListItem()
                        }
                        item { Spacer(modifier = Modifier.width(10.dp)) }
                    }
                }
            }
            Divider(thickness = 1.dp, color = LightBlue)

        }
    }
}

@Preview
@Composable
fun previewRucksack() {
    val context = LocalContext.current
    RucksackScreen(navController = NavController(context))
}
