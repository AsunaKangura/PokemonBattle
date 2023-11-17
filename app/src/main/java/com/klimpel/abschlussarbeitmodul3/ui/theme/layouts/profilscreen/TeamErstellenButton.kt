package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profilscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue

@Composable
fun TeamErstellenButton(navController: NavController){
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.1f)
    ){
        Button(
            onClick = { navController.navigate(Screen.Teamerstellen.route) },
            colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .shadow(10.dp, clip = true)

        ) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = "")
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Team erstellen")
        }
    }
}

@Composable
fun TeamErstellenButtonDisabeled(){
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.1f)
    ){
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
            modifier = Modifier
                .fillMaxWidth(0.7f),
            enabled = false
        ) {
            Text(text = "Maximal Teamanzahl erreicht")
        }
    }
}