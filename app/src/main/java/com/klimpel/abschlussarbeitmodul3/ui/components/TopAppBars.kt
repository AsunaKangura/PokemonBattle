package com.klimpel.abschlussarbeitmodul3.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profil.AvatarRow
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.auth
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.ProfilViewModel
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel
import com.klimpel.pokemonbattlefinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(pageTitle: Int, navController: NavController, viewModel: ProfilViewModel = hiltViewModel()) {
    val context = LocalContext.current

    val setAvatar = remember { mutableIntStateOf(viewModel.findAvatar2()) }
    val showMenu = remember { mutableStateOf(false) }
    val openAliasDialog = remember { mutableStateOf(false) }
    val openAvatarDialog = remember { mutableStateOf(false) }
    val aliasText = remember { mutableStateOf(viewModel.currentUser?.alias) }
    val avatarList by remember { viewModel.avatarList }

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
                onClick = { showMenu.value = true },
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

            if (openAliasDialog.value) {
                showMenu.value = false
                Dialog(onDismissRequest = { openAliasDialog.value = false }) {
                    Card(
                        modifier = Modifier
                            .width(calcDp(percentage = 0.7f, dimension = Dimension.Width))
                            .height(calcDp(percentage = 0.4f, dimension = Dimension.Height)),
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
                        ConstraintLayout(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            val (titel, avatar, alias, btnsave) = createRefs()
                            // Profil Titel
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White,
                                ),
                                border = BorderStroke(
                                    4.dp, MaterialTheme.colorScheme.primary
                                ),
                                modifier = Modifier
                                    .width(180.dp)
                                    .height(50.dp)
                                    .constrainAs(titel) {
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                    },
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
                                        text = stringResource(id = R.string.alaischange),
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }

                            OutlinedTextField(
                                value = aliasText.value.toString(),
                                onValueChange = { aliasText.value = it },
                                label = {
                                    Text(
                                        "Neuer Alias",
                                        fontSize = 14.sp
                                    )
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = LightBlue,
                                    unfocusedBorderColor = Color.Black,
                                    focusedLabelColor = LightBlue,
                                    unfocusedLabelColor = Color.Black,
                                    textColor = Color.Black
                                ),
                                modifier = Modifier
                                    .constrainAs(avatar) {
                                        centerVerticallyTo(parent)
                                        centerHorizontallyTo(parent)
                                    }
                                    .padding(horizontal = 40.dp)
                            )

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .constrainAs(btnsave) {
                                        bottom.linkTo(parent.bottom, 20.dp)
                                    }
                            ) {
                                GradientButton(onClick = {
                                    if (aliasText.value?.isNotEmpty() == true) {
                                        viewModel.currentUser?.alias = aliasText.value.toString()
                                        viewModel.updateFireStoreUser(context)
                                    }
                                    openAliasDialog.value = false
                                }, text = "Speichern")
                            }

                        }
                    }
                }
            }

            if (openAvatarDialog.value) {
                showMenu.value = false
                Dialog(onDismissRequest = { openAvatarDialog.value = false }) {
                    Card(
                        modifier = Modifier
                            .width(calcDp(percentage = 0.7f, dimension = Dimension.Width))
                            .height(calcDp(percentage = 0.7f, dimension = Dimension.Height)),
                        shape = RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 20.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 50.dp
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                    ) {
                        ConstraintLayout(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            val (titel, btnsave, listViewAvatar) = createRefs()
                            // Profil Titel
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                border = BorderStroke(4.dp, LightBlue),
                                modifier = Modifier
                                    .width(180.dp)
                                    .height(50.dp)
                                    .constrainAs(titel) {
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                    },
                                shape = RoundedCornerShape(
                                    topStart = 50.dp,
                                    topEnd = 0.dp,
                                    bottomStart = 0.dp,
                                    bottomEnd = 50.dp
                                ),
                                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceEvenly,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.avatarchange),
                                        color = LightBlue,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.7f)
                                    .padding(horizontal = 20.dp)
                                    .constrainAs(listViewAvatar) {
                                        top.linkTo(titel.bottom)
                                        centerVerticallyTo(parent)
                                    }
                            ) {
                                LazyColumn(
                                    contentPadding = PaddingValues(0.dp)
                                ) {

                                    val itemCount = viewModel.loadListOfAvatar().size
                                    items(itemCount) {index ->
                                        AvatarRow(
                                            rowIndex = index,
                                            entries = avatarList,
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                    }
                                }
                            }

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .constrainAs(btnsave) {
                                        bottom.linkTo(parent.bottom, 15.dp)
                                    }
                            ) {
                                GradientButton(onClick = {
                                    viewModel.updateFireStoreUser(context)
                                    openAvatarDialog.value = false
                                    navController.navigate(Screen.HomeScreen.route)
                                }, text = "Speichern")
                            }
                        }
                    }
                }
            }

            // DropDown Menü
            DropdownMenu(
                expanded = showMenu.value,
                onDismissRequest = { showMenu.value = false },
                modifier = Modifier
                    .width(calcDp(percentage = 0.6f, dimension = Dimension.Width))
                    .height(calcDp(percentage = 0.6f, dimension = Dimension.Height))
                    .background(LightBlue)
            ) {

                // Profil Details
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(calcDp(percentage = 0.10f, dimension = Dimension.Height))
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
                            Image(
                                painterResource(id = setAvatar.intValue),
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
                                viewModel.currentUser?.let {
                                    Text(
                                        text = it.alias, modifier = Modifier.padding(top = 8.dp)
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .fillMaxHeight()
                                            .fillMaxWidth(0.5f),
                                    ) {
                                        Text(text = "PokeDollar:", fontSize = 12.sp)
                                        Text(text = "Tickets:", fontSize = 12.sp)
                                    }
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .fillMaxHeight()
                                            .fillMaxWidth(),
                                    ) {
                                        Text(
                                            text = viewModel.currentUser?.pokedollar.toString() + " $",
                                            fontSize = 12.sp,
                                            color = DeepRed,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = viewModel.currentUser?.pokemontickets.toString()+" Stk.",
                                            fontSize = 12.sp,
                                            color = DeepRed,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Divider(thickness = 3.dp, color = LightBlue)
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.alaischange)) },
                    onClick = { openAliasDialog.value = true },
                    modifier = Modifier
                        .height(calcDp(percentage = 0.08f, dimension = Dimension.Height))
                        .background(Color.White),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                )
                Divider(color = LightBlue)
                DropdownMenuItem(
                    text = { Text(text = "Avatar ändern") },
                    onClick = { openAvatarDialog.value = true },
                    modifier = Modifier
                        .height(calcDp(percentage = 0.08f, dimension = Dimension.Height))
                        .background(Color.White),
                    leadingIcon = {
                        Image(
                            painterResource(id = setAvatar.intValue),
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                        )
                    }
                )
                Divider(color = LightBlue)
                DropdownMenuItem(
                    text = { Text(text = "Rucksack") },
                    onClick = {  },
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
                    text = { Text(text = "Team Übersicht") },
                    onClick = { navController.navigate(Screen.Teamubersicht.route)},
                    modifier = Modifier
                        .height(calcDp(percentage = 0.08f, dimension = Dimension.Height))
                        .background(Color.White),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Groups,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                )
                Divider(color = LightBlue)
                DropdownMenuItem(
                    text = { Text(text = "Meine Pokemon") },
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
                    text = { Text(text = "Ausloggen") },
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

        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Arrow",
                    tint = LightBlue
                )
            }
        },
    )
}




