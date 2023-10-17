package com.klimpel.abschlussarbeitmodul3.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightYellow

@Composable
fun GradientButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    textcolor: Color = Color.White,
    gradient: Brush = Brush.linearGradient(listOf(LightBlue, LightYellow)),
    cornerRadius: Dp = 0.dp,
    border: Dp = 2.dp,
    bordercolor: Color = Color.White,
    paddingx: Dp = 80.dp,
    paddingy: Dp = 8.dp,
    fontSize: TextUnit = 16.sp,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(
        topStart = 50.dp,
        topEnd = 20.dp,
        bottomStart = 20.dp,
        bottomEnd = 50.dp
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