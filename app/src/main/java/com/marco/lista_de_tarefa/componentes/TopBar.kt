package com.marco.lista_de_tarefa.componentes

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marco.lista_de_tarefa.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    text: String
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = text,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp))
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Purple40,
        ),
        modifier = Modifier
            .padding(10.dp)
            .height(50.dp)
            .clip(shape = RoundedCornerShape(12.dp))
    )
}