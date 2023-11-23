package com.klimpel.pokemonbattlefinal.ui.theme.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.components.TopAppBar
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.homescreen.CategoryCard
import com.klimpel.pokemonbattlefinal.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current

    AbschlussarbeitModul3Theme {
        var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
        Scaffold(
            topBar = { TopAppBar(R.string.homescreen, navController) },
            containerColor = LightBlueBackground,
        ) { innerPadding ->
            Divider(thickness = 4.dp, color = LightBlue, modifier = Modifier.padding(innerPadding))
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding), verticalArrangement = Arrangement.SpaceEvenly
            ) {
                // Pokemon Battle
                CategoryCard(onClick = { }, text = R.string.pokemonbattlecard, image = R.drawable.pokemon_battle_pikachu_mewtwo_large)
                // Pokemon Store
                CategoryCard(onClick = { navController.navigate(Screen.Store.route) }, text = R.string.pokestorecard, image = R.drawable.pokemon_centre_launch_edited_large)
                // PokeDex Card
                CategoryCard(onClick = { navController.navigate(Screen.Pokedex.route) }, text = R.string.pokedexcard, image = R.drawable.pokemondex)
            }

        }
    }
}