package com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.profil

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.klimpel.abschlussarbeitmodul3.data.models.Avatar


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
