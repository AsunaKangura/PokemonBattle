package com.klimpel.abschlussarbeitmodul3.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.auth
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.ProfilViewModel
import com.klimpel.pokemonbattlefinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    pageTitle: Int,
    navController: NavController,
    viewModel: ProfilViewModel = hiltViewModel()
) {
    val setAvatar = remember { mutableIntStateOf(viewModel.findAvatarInt(viewModel.currentUser?.avatar.toString())) }
    val showMenu = remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = pageTitle),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = LightBlue,
                fontWeight = FontWeight.Bold,
                fontFamily = pokemonFontFamily,
            )
        },

        actions = {
            IconButton(
                onClick = {
                    showMenu.value = true
                },
                modifier = Modifier
                    .padding(end = 10.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
            ) {
                Image(
                    painterResource(id = setAvatar.intValue),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(0.8f)
                )
            }

            // DropDown Menü
            DropdownMenu(
                expanded = showMenu.value,
                onDismissRequest = { showMenu.value = false },
                modifier = Modifier
                    .width(calcDp(percentage = 0.6f, dimension = Dimension.Width))
                    .height(calcDp(percentage = 0.35f, dimension = Dimension.Height))
                    .background(LightBlue)
            ) {
                Divider(thickness = 3.dp, color = LightBlue)
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.dropmenuitemprofil)) },
                    onClick = { navController.navigate(Screen.ProfilScreen.route) },
                    modifier = Modifier
                        .height(calcDp(percentage = 0.08f, dimension = Dimension.Height))
                        .background(Color.White),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                )
                Divider(color = LightBlue)
                DropdownMenuItem(
                    text = { Text(stringResource(id = R.string.dropmenuitemrucksack)) },
                    onClick = {navController.navigate(Screen.Rucksack.route) },
                    modifier = Modifier
                        .height(calcDp(percentage = 0.08f, dimension = Dimension.Height))
                        .background(Color.White),
                    leadingIcon = {
                        Image(
                            painterResource(id = R.drawable.backpack),
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                        )
                    }
                )
                Divider(color = LightBlue)
                DropdownMenuItem(
                    text = { Text(stringResource(id = R.string.dropmenuitemmeinepokemon)) },
                    onClick = { navController.navigate(Screen.MeinePokemon.route) },
                    modifier = Modifier
                        .height(calcDp(percentage = 0.08f, dimension = Dimension.Height))
                        .background(Color.White),
                    leadingIcon = {
                        Image(
                            painterResource(id = R.drawable.ic_pokeball),
                            contentDescription = null
                        )
                    }
                )
                Divider(color = LightBlue)
                DropdownMenuItem(
                    text = { Text(stringResource(id = R.string.dropmenuitemlogout)) },
                    onClick = {
                        auth.signOut()
                        navController.navigate(Screen.WelcomeScreen.route)
                    },
                    modifier = Modifier
                        .height(calcDp(percentage = 0.08f, dimension = Dimension.Height))
                        .background(Color.White),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Logout,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                )
            }
        },



        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarTitelBackArrow(pageTitle: Int, navController: NavController) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = pageTitle),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = LightBlue,
                fontWeight = FontWeight.Bold,
                fontFamily = pokemonFontFamily,
            )
        },

        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Arrow 2",
                    tint = LightBlue
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarTitelBackArrowWarenkorb(pageTitle: Int, navController: NavController) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = pageTitle),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = LightBlue,
                fontWeight = FontWeight.Bold,
                fontFamily = pokemonFontFamily,
            )
        },

        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(Screen.Store.route)
                }
            ){
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = LightBlue
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarTitelBackArrowStore(pageTitle: Int, navController: NavController) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = pageTitle),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = LightBlue,
                fontWeight = FontWeight.Bold,
                fontFamily = pokemonFontFamily,
            )
        },

        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                }
            ){
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = LightBlue
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarTitelBackArrowProduktScreen(pageTitle: String, navController: NavController) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = pageTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = LightBlue,
                fontWeight = FontWeight.Bold,
                fontFamily = pokemonFontFamily,
            )
        },

        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(Screen.Store.route)
                }
            ){
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = LightBlue
                )
            }
        },
    )
}




