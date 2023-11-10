package com.klimpel.pokemonbattlefinal.ui.theme.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.components.CardWithAnimatedBorder
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.EvoloutionColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.SpdColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily
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

                /*
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

                 */
                /*
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.08f)
                        .constrainAs(ball) {
                            top.linkTo(logo.bottom, 50.dp)
                            centerHorizontallyTo(parent)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = LightBlue,
                    ),
                    shape = RoundedCornerShape(topStart = 50.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 50.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Gen 1",
                            fontFamily = pokemonFontFamily,
                            fontSize = 40.sp,
                            color = Color.White,
                        )
                    }
                }

                 */

                CardWithAnimatedBorder (
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.08f)
                        .constrainAs(ball) {
                            top.linkTo(logo.bottom, 50.dp)
                            centerHorizontallyTo(parent)
                        },
                    borderColors = EvoloutionColor
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Gen 1",
                            fontFamily = pokemonFontFamily,
                            fontSize = 40.sp,
                            color = Color.White,
                        )
                    }
                }

            }
        }
    }
}
@Preview
@Composable
fun testView(){
    val context = LocalContext.current
    SplashScreen(navController = NavController(context))
}