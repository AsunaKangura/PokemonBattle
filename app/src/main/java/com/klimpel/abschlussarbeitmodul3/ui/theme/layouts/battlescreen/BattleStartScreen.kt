package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.battlescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.ui.components.TopAppBarTitelBackArrow
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.pokemonbattlefinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BattleStartScreen(navController: NavController) {


    AbschlussarbeitModul3Theme {
        Scaffold(
            topBar = {
                TopAppBarTitelBackArrow(
                    pageTitle = R.string.titelbattelstartscreen,
                    navController = navController
                )
            },
            containerColor = LightBlueBackground,
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .paint(
                            painterResource(R.drawable.vsschriftzug),
                        )
                ) {
                    val (dividertopappbar, content) = createRefs()

                    Divider(
                        thickness = 4.dp,
                        color = LightBlue,
                        modifier = Modifier.constrainAs(dividertopappbar) { top.linkTo(parent.top) }
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .height(200.dp)
                            .padding(horizontal = 20.dp)
                            .constrainAs(content) {
                                top.linkTo(parent.top, 80.dp)
                            },
                        shape = RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 20.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 50.dp
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ),
                    ) {

                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun previewScreen() {

    val context = LocalContext.current
    BattleStartScreen(navController = NavController(context))
}