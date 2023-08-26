package com.marco.lista_de_tarefa.componentes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.marco.lista_de_tarefa.ui.theme.Light_Blue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaixaDeTexto(
    value: String,
    onValueChange: (String) ->Unit,
    modifier: Modifier,
    label: String,
    maxLine: Int,
    keyBoardType: KeyboardType
) {

    val isDarkMode = isSystemInDarkTheme()

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier,
        label = {
            Text(text = label)
        },
        maxLines = maxLine,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = if (isDarkMode) Color.White else Color.Black, // Altera a cor do texto com base no modo de tema
            focusedBorderColor = Light_Blue,
            focusedLabelColor =  Light_Blue,
            cursorColor = Light_Blue
        ),
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyBoardType
        )
    )

}

