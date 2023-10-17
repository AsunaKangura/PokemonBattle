package com.klimpel.abschlussarbeitmodul3.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.auth
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.pokemonbattlefinal.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(pageTitle: Int, navController: NavController) {

    val showMenu = remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = pageTitle),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = DeepRed,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(
                onClick = { showMenu.value = true },
                modifier = Modifier
                    .padding(end = 10.dp)
                    .border(2.dp, DeepRed, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = null,
                    tint = DeepRed
                )
            }

            DropdownMenu(
                expanded = showMenu.value,
                onDismissRequest = { showMenu.value = false },
                modifier = Modifier
                    .width(calcDp(percentage = 0.6f, dimension = Dimension.Width))
                    .height(calcDp(percentage = 0.5f, dimension = Dimension.Height))
                    .background(DeepRed)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(calcDp(percentage = 0.1f, dimension = Dimension.Height))
                        .background(Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(calcDp(percentage = 0.2f, dimension = Dimension.Width)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                            ) {
                                Text(text = "AsunaKangura", modifier = Modifier.padding(top = 8.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth(0.5f),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = "PokeDollar:", fontSize = 14.sp)
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "10.000 $",
                                            fontSize = 14.sp,
                                            color = DeepRed,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Divider(thickness = 3.dp, color = DeepRed)
                DropdownMenuItem(
                    text = { Text(text = "Profil bearbeiten") },
                    onClick = { },
                    modifier = Modifier
                        .height(calcDp(percentage = 0.1f, dimension = Dimension.Height))
                        .background(Color.White),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                )
                Divider(color = DeepRed)

                DropdownMenuItem(
                    text = { Text(text = "Team Übersicht") },
                    onClick = { },
                    modifier = Modifier
                        .height(calcDp(percentage = 0.09f, dimension = Dimension.Height))
                        .background(Color.White),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Groups,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                )
                Divider(color = DeepRed)
                DropdownMenuItem(
                    text = { Text(text = "Meine Pokemons") },
                    onClick = { },
                    modifier = Modifier
                        .height(calcDp(percentage = 0.09f, dimension = Dimension.Height))
                        .background(Color.White),
                    leadingIcon = {
                        Image(
                            painterResource(id = R.drawable.ic_pokeball),
                            contentDescription = null
                        )
                    }
                )
                Divider(color = DeepRed)
                DropdownMenuItem(
                    text = { Text(text = "Ausloggen") },
                    onClick = {
                        auth.signOut()
                        navController.navigate(Screen.WelcomeScreen.route)
                    },
                    modifier = Modifier
                        .height(calcDp(percentage = 0.09f, dimension = Dimension.Height))
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

    val showMenu = remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = pageTitle),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = DeepRed,
                fontWeight = FontWeight.Bold
            )
        },

        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Arrow",
                    tint = DeepRed
                )
            }
        },
    )
}