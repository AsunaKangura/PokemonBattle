package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.meinepokemon

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.viewmodels.MeinePokemonViewModel
import com.klimpel.abschlussarbeitmodul3.viewmodels.TeamViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeinePokemon(navController: NavController, viewModelTeam: TeamViewModel = hiltViewModel()
){
    val ownedPokemonList = viewModelTeam.pokemonUbersicht.collectAsState()

    if (ownedPokemonList.value.isNotEmpty()){
        OwnedPokemonContent(navController = navController)
    }else {
        NoOwnedPokemon(navController)
    }
}