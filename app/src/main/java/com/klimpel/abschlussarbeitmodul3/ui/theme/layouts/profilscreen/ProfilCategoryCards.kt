package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.components.CategoryCardKlein
import com.klimpel.pokemonbattlefinal.R

@Composable
fun ProfilCategoryCards(navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight()
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight()
                .padding(end = 10.dp)
        ){
            CategoryCardKlein(onClick = { }, text = R.string.pokestorecard, image = R.drawable.pokemon_centre_launch_edited_large)
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 10.dp)
        ){
            CategoryCardKlein(onClick = { navController.navigate(Screen.Pokedex.route) }, text = R.string.pokedexcard, image = R.drawable.pokemondex)
        }
    }
}