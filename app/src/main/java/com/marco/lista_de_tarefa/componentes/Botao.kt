package com.marco.lista_de_tarefa.componentes

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marco.lista_de_tarefa.ui.theme.Light_Blue
import com.marco.lista_de_tarefa.ui.theme.White

@Composable
fun Btn(
    onClick: () -> Unit,
    modifier: Modifier,
    texto: String
) {
    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Light_Blue,
            contentColor = White
        ),
        shape = RoundedCornerShape(12.dp)
        ) {
        Text(
            text = texto,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}