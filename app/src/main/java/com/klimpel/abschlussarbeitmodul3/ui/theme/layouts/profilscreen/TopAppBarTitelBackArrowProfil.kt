package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.pokemonFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarTitelBackArrowProfil(pageTitle: Int, navController: NavController) {

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