package com.klimpel.pokemonbattlefinal.ui.theme.layouts



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.components.EditTextField
import com.klimpel.abschlussarbeitmodul3.ui.components.GradientButton
import com.klimpel.abschlussarbeitmodul3.ui.components.TitelCard
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.viewmodels.AllgemeinesViewModel
import com.klimpel.pokemonbattlefinal.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, viewModel: AllgemeinesViewModel= hiltViewModel()) {

    val context = LocalContext.current

    AbschlussarbeitModul3Theme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(id = R.drawable.splashout),
                        contentScale = ContentScale.Crop
                    )
            ) {
                val (logo, contentbox) = createRefs()

                Image(
                    painterResource(id = R.drawable.pokemon_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .constrainAs(logo) {
                            centerHorizontallyTo(parent)
                            top.linkTo(parent.top, 80.dp)
                        }
                )

                Card(
                    modifier = Modifier
                        .width(300.dp)
                        .height(400.dp)
                        .constrainAs(contentbox) {
                            top.linkTo(logo.bottom, 50.dp)
                            centerHorizontallyTo(parent)
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
                    colors = CardDefaults.cardColors(Color.White.copy(alpha = 0.9f))
                ) {

                    TitelCard(titel = R.string.btn_register)

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        var textStateEmail by remember { mutableStateOf("") }
                        var textStatePassword by remember { mutableStateOf("") }

                        val coroutineScope = rememberCoroutineScope()

                        EditTextField(value = textStateEmail, onValueChange = {values -> textStateEmail = values}, label = "E-Mail Adresse eingeben")

                        Spacer(modifier = Modifier.height(10.dp))

                        EditTextField(value = textStatePassword, onValueChange = {values -> textStatePassword = values}, label = "Passwort eingeben")

                        Spacer(modifier = Modifier.height(30.dp))

                        GradientButton(
                            onClick = {
                                viewModel.register(context,textStateEmail, textStatePassword)
                                coroutineScope.launch {
                                    delay(1000)
                                    navController.navigate(Screen.HomeScreen.route)
                                }
                            },
                            text = stringResource(id = R.string.btn_register),
                            bordercolor = LightBlue,
                            gradient = Brush.linearGradient(listOf(Color.White, Color.White)),
                            textcolor = LightBlue,
                            paddingx = 60.dp
                        )
                    }
                }
            }
        }
    }
}