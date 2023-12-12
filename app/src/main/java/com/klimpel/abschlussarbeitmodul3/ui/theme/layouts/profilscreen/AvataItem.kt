package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.klimpel.abschlussarbeitmodul3.data.models.Avatar
import com.klimpel.abschlussarbeitmodul3.viewmodels.ProfilViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvatarItem(avatar: Avatar,context: Context, viewModelProfil: ProfilViewModel= hiltViewModel()) {
    Card(
        onClick = { viewModelProfil.updateAvatar(avatar.name, context)},
        modifier = Modifier.size(80.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painterResource(id = avatar.imageResource),
                contentDescription = avatar.name,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}