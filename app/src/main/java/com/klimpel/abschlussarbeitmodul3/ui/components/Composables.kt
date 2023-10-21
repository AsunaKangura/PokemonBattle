package com.klimpel.abschlussarbeitmodul3.ui.components


import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.klimpel.abschlussarbeitmodul3.data.models.Avatar
import com.klimpel.abschlussarbeitmodul3.data.models.PokedexListEntry
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightYellow
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.pokedexscreen.PokemonCard
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.ProfilViewModel
import com.klimpel.pokemonbattlefinal.R
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


@Composable
fun GradientButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    textcolor: Color = DeepRed,
    gradient: Brush = Brush.linearGradient(listOf(Color.White, Color.White)),
    cornerRadius: Dp = 0.dp,
    border: Dp = 2.dp,
    bordercolor: Color = DeepRed,
    paddingx: Dp = 20.dp,
    paddingy: Dp = 8.dp,
    fontSize: TextUnit = 16.sp,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp,
        bottomStart = 20.dp,
        bottomEnd = 20.dp
    )
) {
    Surface(
        modifier = modifier,
        color = Color.Transparent,
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(cornerRadius),
            onClick = { onClick() }
        ) {
            Box(
                modifier = Modifier
                    .border(border, bordercolor, roundedCornerShape)
                    .background(
                        brush = gradient,
                        shape = roundedCornerShape
                    )
                    .padding(horizontal = paddingx, vertical = paddingy)
                    .clip(roundedCornerShape),
                contentAlignment = Alignment.Center

            ) {
                Text(text = text, color = textcolor, fontSize = fontSize)
            }
        }
    }

}

@Composable
fun ListItemAvatar(
    entry: Avatar,
    viewModel: ProfilViewModel = hiltViewModel()
) {

    val isClicked = remember { mutableStateOf(false) }
    var color: Color = Color.White
    var clickCount = 0

    val context = LocalContext.current

    color = if (isClicked.value){
        Color.Green
    } else {
        Color.White
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                isClicked.value = !isClicked.value
                viewModel.currentUser?.avatar = entry.name
            },
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, DeepRed, RoundedCornerShape(10.dp))
        ) {
            Box(
                modifier = Modifier
                    .width(calcDp(percentage = 0.2f, dimension = Dimension.Width))
                    .fillMaxHeight()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        painterResource(
                            id = entry.imageResource
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp)
                ) {
                    Text(
                        text = entry.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = DeepRed
                    )
                }
            }
        }
    }
}

@Composable
fun AvatarRow(
    rowIndex: Int,
    entries: List<Avatar>,
    modifier: Modifier = Modifier,
) {
    Column {
        Row {

            ListItemAvatar(
                entry = entries[rowIndex],
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Preview
@Composable
fun test() {

}