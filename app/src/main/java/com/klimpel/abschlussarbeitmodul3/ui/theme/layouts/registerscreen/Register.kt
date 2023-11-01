package com.klimpel.pokemonbattlefinal.ui.theme.layouts


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.components.GradientButton
import com.klimpel.abschlussarbeitmodul3.ui.components.messageDialogError
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.auth
import com.klimpel.pokemonbattlefinal.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {

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
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                        border = BorderStroke(
                            4.dp, LightBlue
                        ),
                        modifier = Modifier
                            .width(200.dp)
                            .height(60.dp),
                        shape = RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 0.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 50.dp
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.btn_register),
                                color = LightBlue,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = pokemonFontFamily,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        var textStateEmail by remember { mutableStateOf(TextFieldValue("")) }
                        var textStatePassword by remember { mutableStateOf(TextFieldValue("")) }

                        OutlinedTextField(
                            value = textStateEmail,
                            onValueChange = { textStateEmail = it },
                            label = { Text("E-Mail Adresse eingeben", fontSize = 14.sp) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = LightBlue, unfocusedBorderColor = Color.Black, focusedLabelColor = LightBlue, unfocusedLabelColor = Color.Black, textColor = Color.Black),
                            modifier = Modifier
                                .padding(horizontal = 40.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = textStatePassword,
                            onValueChange = { textStatePassword = it },
                            visualTransformation = PasswordVisualTransformation(),
                            label = { Text("Passwort eingeben", fontSize = 14.sp) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = LightBlue, unfocusedBorderColor = Color.Black, focusedLabelColor = LightBlue, unfocusedLabelColor = Color.Black, textColor = Color.Black),
                            modifier = Modifier
                                .padding(horizontal = 40.dp)
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        GradientButton(
                            onClick = {
                                val email = textStateEmail.text
                                val password = textStatePassword.text

                                if(email.isNotEmpty() && password.isNotEmpty() ) {
                                    auth.createUserWithEmailAndPassword(email, password)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                navController.navigate(Screen.WelcomeScreen.route)
                                            } else {
                                                messageDialogError(context, "Registrieren fehlgeschlagen")
                                            }
                                        }
                                }else{
                                    messageDialogError(context, "Du musst alle Felder ausfüllen")
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


@Preview(showBackground = true)
@Composable
fun previewRegisterScreen() {
    val context = LocalContext.current
    RegisterScreen(navController = NavController(context))
}