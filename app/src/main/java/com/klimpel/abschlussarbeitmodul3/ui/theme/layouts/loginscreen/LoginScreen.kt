
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.components.GradientButton
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.auth
import com.klimpel.abschlussarbeitmodul3.util.adminmail
import com.klimpel.abschlussarbeitmodul3.util.adminpw
import com.klimpel.abschlussarbeitmodul3.viewmodels.ProfilViewModel
import com.klimpel.pokemonbattlefinal.R


@Preview
@Composable
fun previewLogin() {
    val context = LocalContext.current
    LoginScreen(navController = NavController(context))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: ProfilViewModel = hiltViewModel()
) {
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

                val context = LocalContext.current

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
                ){
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                        border = BorderStroke(
                            4.dp, DeepRed
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
                                text = stringResource(id = R.string.text_Login),
                                color = DeepRed,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }

                    Column(
                        modifier= Modifier
                            .fillMaxSize()
                    ) {
                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            /*
                            var textStateUsername by remember { mutableStateOf(TextFieldValue("")) }
                            var textStatePassword by remember { mutableStateOf(TextFieldValue("")) }

                             */

                            var textStateUsername = adminmail
                            var textStatePassword = adminpw

                            val (inputEmail, inputPassword, btnlogin) = createRefs()

                            OutlinedTextField(
                                value = textStateUsername,
                                onValueChange = { textStateUsername = it },
                                label = { Text(
                                    "E-Mail Adresse eingeben",
                                    fontSize = 14.sp
                                ) },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = DeepRed,
                                    unfocusedBorderColor = Color.Black,
                                    focusedLabelColor = DeepRed,
                                    unfocusedLabelColor = Color.Black,
                                    textColor = Color.Black
                                ),
                                modifier = Modifier
                                    .constrainAs(inputEmail) {
                                        top.linkTo(parent.top, 60.dp)
                                        centerHorizontallyTo(parent)
                                    }
                                    .padding(horizontal = 40.dp)
                            )

                            OutlinedTextField(
                                value = textStatePassword,
                                onValueChange = { textStatePassword = it },
                                visualTransformation = PasswordVisualTransformation(),
                                label = { Text(
                                    "Passwort eingeben",
                                    fontSize = 14.sp
                                ) },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = DeepRed,
                                    unfocusedBorderColor = Color.Black,
                                    focusedLabelColor = DeepRed,
                                    unfocusedLabelColor = Color.Black,
                                    textColor = Color.Black
                                ),
                                modifier = Modifier
                                    .constrainAs(inputPassword) {
                                        top.linkTo(inputEmail.bottom, 20.dp)
                                        centerHorizontallyTo(parent)
                                    }
                                    .padding(horizontal = 40.dp)
                            )

                            GradientButton(
                                onClick = {
                                    // später .text in der If und beim Trim dazwischen schreiben
                                    if (textStateUsername.isNotEmpty() && textStatePassword.isNotEmpty()) {
                                        auth.signInWithEmailAndPassword(
                                            textStateUsername.trim(),
                                            textStatePassword.trim()
                                        )
                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    navController.navigate(Screen.HomeScreen.route)
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "Logindaten waren nicht korrekt",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
                                            }
                                        viewModel.updateUser(auth.currentUser?.uid.toString())
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Du musst alle Felder ausfüllen",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                },
                                text = stringResource(id = R.string.btn_login),
                                bordercolor = DeepRed,
                                gradient = Brush.linearGradient(listOf(Color.White, Color.White)),
                                textcolor = DeepRed,
                                modifier = Modifier
                                    .constrainAs(btnlogin){
                                        top.linkTo(inputPassword.bottom, 30.dp)
                                        centerHorizontallyTo(parent)
                                    }
                            )
                        }
                    }


                }
            }
        }
    }
}