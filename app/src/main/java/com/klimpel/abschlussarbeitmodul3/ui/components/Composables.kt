package com.klimpel.abschlussarbeitmodul3.ui.components



import androidx.compose.foundation.background
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize


import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightYellow
import com.klimpel.abschlussarbeitmodul3.util.Dimension
import com.klimpel.abschlussarbeitmodul3.util.calcDp
import com.klimpel.abschlussarbeitmodul3.viewmodels.ProfilViewModel


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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomExposedDropdownMenuBox(
    viewModel: ProfilViewModel = hiltViewModel()
){

    val options = viewModel.loadListOfAvatars()
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        ConstraintLayout (
            modifier = Modifier
                .fillMaxSize()
        ) {

            val (text, menu) = createRefs()

            OutlinedTextField(
                value = selectedOptionText,
                onValueChange = { },
                readOnly = true,
                label = { Text("Avatar auswählen", fontSize = 14.sp) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = DeepRed,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = DeepRed,
                    unfocusedLabelColor = Color.Black,
                    textColor = Color.Black
                ),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .menuAnchor()
                    .constrainAs(text){
                        top.linkTo(parent.top)
                    }
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .constrainAs(menu){
                        top.linkTo(text.bottom)
                    }
            ) {
                options.forEach { label ->
                    DropdownMenuItem(
                        text = { Text(text = label) },
                        onClick = {
                            selectedOptionText = label
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun test() {

}