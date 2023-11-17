package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.ui.components.EditTextField
import com.klimpel.abschlussarbeitmodul3.ui.components.GradientButton
import com.klimpel.abschlussarbeitmodul3.ui.components.TitelCard
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.ProfilViewModel
import com.klimpel.pokemonbattlefinal.R

@Composable
fun ProfilInfosTopSection(
    navController: NavController,
    viewModelprofil: ProfilViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val aliasText = remember { mutableStateOf(viewModelprofil.currentUser?.alias) }
    val openAliasDialog = remember { mutableStateOf(false) }
    val openAvatarDialog = remember { mutableStateOf(false) }
    val avatarList by remember { viewModelprofil.avatarList }

    if (openAliasDialog.value) {
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
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
            ) {

                Row {
                    // Profil Titel
                    TitelCard(titel = R.string.alaischange)
                }
                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row {
                        EditTextField(
                            value = aliasText.value.toString(),
                            onValueChange = { values -> aliasText.value = values },
                            label = "Alias ändern"
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        GradientButton(onClick = {
                            if (aliasText.value != "") {
                                viewModelprofil.updateAlias(aliasText.value.toString(), context)
                                openAliasDialog.value = false
                            }
                        }, text = "Speichern")
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }

    if (openAvatarDialog.value) {
        viewModelprofil.loadListOfAvatar()
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
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                Row {
                    // Profil Titel
                    TitelCard(titel = R.string.avatarchange)
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(80.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.9f),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        content = {
                            val itemcount = avatarList.size

                            items(itemcount) {
                                val pokemonname = avatarList[it]
                                AvatarItem(pokemonname, context)
                            }
                        }
                    )
                    GradientButton(onClick = { openAvatarDialog.value = false }, text = "Schließen")
                }
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.4f)
            .padding(top = 20.dp, bottom = 20.dp)
            .border(1.dp, color = LightBlue, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
                .padding(10.dp)
        ) {
            Image(
                painterResource(viewModelprofil.findAvatarInt(viewModelprofil.currentUser?.avatar.toString())),
                contentDescription = "",
                modifier = Modifier.size(70.dp)
            )
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Icon(imageVector = Icons.Filled.Edit,
                    contentDescription = "",
                    tint = LightBlue,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            onClick = { openAvatarDialog.value = true }
                        )
                )

            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        // Alias
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
        ) {
            viewModelprofil.currentUser?.alias?.let {
                Text(
                    text = it,
                    color = LightBlue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(imageVector = Icons.Filled.Edit,
                contentDescription = "",
                tint = LightBlue,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        onClick = { openAliasDialog.value = true }
                    )
            )
        }
        // Pokedollar und PokemonTickets
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .fillMaxHeight()
                ) {
                    Text(
                        text = "PokeDollar:",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Tickets:", fontSize = 16.sp, color = Color.Black)
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Text(
                        text = viewModelprofil.currentUser?.pokedollar.toString() + " $",
                        fontSize = 14.sp,
                        color = DeepRed,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = viewModelprofil.currentUser?.pokemontickets.toString() + " Stk.",
                        fontSize = 14.sp,
                        color = DeepRed,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

    }


}