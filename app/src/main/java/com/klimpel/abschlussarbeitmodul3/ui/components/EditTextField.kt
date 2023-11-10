package com.klimpel.abschlussarbeitmodul3.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextField(value: String, onValueChange: (String: String) -> Unit, label: String){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 14.sp) },
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = LightBlue, unfocusedBorderColor = Color.Black, focusedLabelColor = LightBlue, unfocusedLabelColor = Color.Black, textColor = Color.Black),
        modifier = Modifier
            .padding(horizontal = 40.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextFieldPW(value: String, onValueChange: (String: String) -> Unit, label: String){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 14.sp) },
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = LightBlue, unfocusedBorderColor = Color.Black, focusedLabelColor = LightBlue, unfocusedLabelColor = Color.Black, textColor = Color.Black),
        modifier = Modifier
            .padding(horizontal = 40.dp),
        visualTransformation = PasswordVisualTransformation()
    )
}