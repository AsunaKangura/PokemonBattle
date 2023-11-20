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
        label = { Text(label, fontSize = 12.sp) },
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = LightBlue, unfocusedBorderColor = Color.Black, focusedLabelColor = LightBlue, unfocusedLabelColor = Color.Black, textColor = Color.Black),
        modifier = Modifier
            .padding(horizontal = 20.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextFieldEnabeled(value: String, onValueChange: (string: String) -> Unit, label: String, enabel: Boolean = false){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = enabel,
        label = { Text(label, fontSize = 12.sp) },
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = LightBlue, unfocusedBorderColor = Color.Black, focusedLabelColor = LightBlue, unfocusedLabelColor = Color.Black, textColor = Color.Black),
        modifier = Modifier
            .padding(horizontal = 20.dp),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextFieldPW(value: String, onValueChange: (String: String) -> Unit, label: String){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 12.sp) },
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = LightBlue, unfocusedBorderColor = Color.Black, focusedLabelColor = LightBlue, unfocusedLabelColor = Color.Black, textColor = Color.Black),
        modifier = Modifier
            .padding(horizontal = 20.dp),
        visualTransformation = PasswordVisualTransformation()
    )
}