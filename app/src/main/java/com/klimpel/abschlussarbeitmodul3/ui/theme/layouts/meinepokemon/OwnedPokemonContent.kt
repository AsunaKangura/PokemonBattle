package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.meinepokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.ui.components.TopAppBarTitelBackArrow
import com.klimpel.abschlussarbeitmodul3.ui.theme.AbschlussarbeitModul3Theme
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.AvailablePokemonListItemMyPokemonm
import com.klimpel.abschlussarbeitmodul3.viewmodels.SearchingViewModel
import com.klimpel.pokemonbattlefinal.R

@Composable
fun OwnedPokemonContent(navController: NavController, viewModelSearch: SearchingViewModel= hiltViewModel()){

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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(innerpadding)
            ) {
                val searchText by viewModelSearch.searchText.collectAsState()
                val ownedPokemon by viewModelSearch.ownedPokemon.collectAsState()
                val isSearching by viewModelSearch.isSearching.collectAsState()
                var isHintDisplayed by remember {
                    mutableStateOf(false)
                }

                Divider(
                    thickness = 4.dp,
                    color = LightBlue,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                ) {
                    BasicTextField(
                        value = searchText,
                        onValueChange = viewModelSearch::onSearchTextChange,
                        maxLines = 1,
                        singleLine = true,
                        textStyle = TextStyle(Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                5.dp,
                                RoundedCornerShape(20.dp)
                            )
                            .background(Color.White)
                            .padding(horizontal = 20.dp, vertical = 12.dp)
                            .onFocusChanged {
                                isHintDisplayed = !isHintDisplayed
                            }
                    )
                    if (isHintDisplayed) {
                        Text(
                            text = "Nach Name, ID, Type suchen",
                            color = LightBlue,
                            modifier = Modifier.padding(
                                horizontal = 20.dp,
                                vertical = 12.dp
                            ),
                            fontSize = 12.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(id = R.string.dropmenuitemmeinepokemon),
                    color = LightBlue,
                    fontWeight = FontWeight.Bold,
                )

                Divider(thickness = 1.dp, color = LightBlue)

                Spacer(modifier = Modifier.height(20.dp))

                if (isSearching) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(115.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.9f),
                        verticalArrangement = Arrangement.spacedBy(0.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        val itemcount = ownedPokemon.size

                        items(itemcount) {
                            AvailablePokemonListItemMyPokemonm(
                                pokemon = ownedPokemon[it]
                            )
                        }
                    }
                }
            }
        }
    }
}