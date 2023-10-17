package com.klimpel.pokemonbattlefinal.ui.theme.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.SpdColor
import com.klimpel.pokemonbattlefinal.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    AbschlussarbeitModul3Theme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(R.drawable.splashout),
                        contentScale = ContentScale.Crop,
                    ),
            ) {

                val (logo, ball, progress) = createRefs()

                Image(
                    painterResource(id = R.drawable.pokemon_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .constrainAs(logo) {
                            centerHorizontallyTo(parent)
                            top.linkTo(parent.top, 220.dp)
                        }
                )
                LaunchedEffect(key1 = true){
                    delay(5000)
                    navController.navigate(Screen.WelcomeScreen.route)
                }

                Image(
                    painterResource(id = R.drawable.ic_pokeball),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(ball) {
                            top.linkTo(logo.bottom, 50.dp)
                            centerHorizontallyTo(parent)
                        }
                        .scale(5f)
                )

                LinearProgressIndicator(
                    modifier = Modifier
                        .constrainAs(progress) {
                            top.linkTo(ball.bottom, 80.dp)
                            centerHorizontallyTo(parent)
                        }
                        .height(15.dp),
                    trackColor = SpdColor,
                    color = Color.White

                )

            }
        }
    }
}