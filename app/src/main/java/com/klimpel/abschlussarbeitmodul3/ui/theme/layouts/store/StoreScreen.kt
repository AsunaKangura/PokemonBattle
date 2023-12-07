package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.store

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.ui.components.TopAppBarTitelBackArrowStore
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.StoreViewModel
import com.klimpel.pokemonbattlefinal.R

@Composable
fun StoreScreen(
    navController: NavController,
    viewModelStore: StoreViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()

    val storeCategoryList by viewModelStore.categoryList.collectAsState()

    Scaffold(
        topBar = { TopAppBarTitelBackArrowStore(R.string.pokestorecard, navController) },
        containerColor = LightBlueBackground,
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .verticalScroll(scrollState)
        ) {
            Divider(thickness = 4.dp, color = LightBlue)

            Spacer(modifier = Modifier.height(calcDp(percentage = 0.02f, dimension = Dimension.Height)))

            GuthabenUndWarenkorb(navController = navController)

            for ( entry in storeCategoryList){
                StoreCategory(navController = navController, categoryName = entry.name, categoryID = entry.id)
            }
        }
        Spacer(modifier = Modifier.height(calcDp(percentage = 0.02f, dimension = Dimension.Height)))

    }
}