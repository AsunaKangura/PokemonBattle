package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.meinepokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonGrindEntry
import com.klimpel.abschlussarbeitmodul3.ui.components.CardWithAnimatedBorderOwnedPokemon
import com.klimpel.abschlussarbeitmodul3.ui.components.TopAppBarTitelBackArrow
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.util.PokemonEvoloutionBorder
import com.klimpel.abschlussarbeitmodul3.viewmodels.MeinePokemonViewModel
import com.klimpel.pokemonbattlefinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnedPokemonContent(navController: NavController, viewModelMeinPokemon: MeinePokemonViewModel = hiltViewModel(), pokemonlist: List<PokemonGrindEntry>){

    AbschlussarbeitModul3Theme {
        Scaffold(
            topBar = {
                TopAppBarTitelBackArrow(
                    pageTitle = R.string.titelmeinepokemon,
                    navController = navController
                )
            },
            containerColor = LightBlueBackground,
        ) { innerpadding ->
            Divider(
                thickness = 4.dp,
                color = LightBlue,
                modifier = Modifier.padding(innerpadding)
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerpadding)
            ) {
                viewModelMeinPokemon.loadOwnedPokemon()
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(130.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.9f),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    content = {
                        val itemcount = pokemonlist.size

                        items(itemcount) {
                            val pokemonname = pokemonlist[it]
                            CardWithAnimatedBorderOwnedPokemon(
                                borderColors = PokemonEvoloutionBorder(pokemonname.name)
                            ) {
                                PokemonGridCard(navController, pokemonlist[it])
                            }
                        }
                    }
                )
            }
        }
    }

}