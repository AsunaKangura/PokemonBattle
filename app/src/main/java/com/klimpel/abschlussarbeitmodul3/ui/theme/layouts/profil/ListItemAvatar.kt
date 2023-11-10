package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profil

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.klimpel.abschlussarbeitmodul3.data.models.Avatar
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.ProfilViewModel

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
                .border(2.dp, LightBlue, RoundedCornerShape(10.dp))
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
                        color = LightBlue
                    )
                }
            }
        }
    }
}