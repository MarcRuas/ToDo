package com.marco.lista_de_tarefa.componentes

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun MainFloat(navController: NavController) {
    FloatingActionButton(
        onClick = {
            navController.navigate("SalvarTarefa")
        },
        shape = CircleShape
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}