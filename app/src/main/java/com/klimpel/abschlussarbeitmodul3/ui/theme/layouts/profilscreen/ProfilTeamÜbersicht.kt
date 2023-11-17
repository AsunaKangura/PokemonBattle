package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.viewmodels.ProfilViewModel

@Composable
fun ProfilTeamUbersicht(
    navController: NavController,
    viewModelProfil: ProfilViewModel = hiltViewModel()
){
    val teamList = viewModelProfil.teamList.collectAsState()

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LazyColumn {

                        val itemcount = teamList.value.size
                        items(itemcount) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 15.dp)
                            ) {
                                ProfilTeamCard(teamList.value[it], navController)
                            }
                        }
                    }
                }
            }
        }
    }
}