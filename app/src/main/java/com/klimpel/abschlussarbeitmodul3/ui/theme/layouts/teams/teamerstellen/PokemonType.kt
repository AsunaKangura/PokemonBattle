package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.teams.teamerstellen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asunakangura.pokemonbattle.data.remote.responses.Type
import com.klimpel.abschlussarbeitmodul3.util.parseTypeToColor
import java.util.Locale



@Composable
fun PokemonTypeItem(types: List<Type>){
    for(type in types) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clip(CircleShape)
                .background(parseTypeToColor(type))
                .height(35.dp)
        ) {
            Text(
                text = type.type.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                },
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}