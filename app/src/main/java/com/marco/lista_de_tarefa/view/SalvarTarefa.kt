package com.marco.lista_de_tarefa.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.marco.lista_de_tarefa.componentes.Btn
import com.marco.lista_de_tarefa.componentes.CaixaDeTexto
import com.marco.lista_de_tarefa.componentes.MainTopBar
import com.marco.lista_de_tarefa.repositore.TarefasRepositorio
import com.marco.lista_de_tarefa.ui.theme.Radio_Btn_Green_Disabled
import com.marco.lista_de_tarefa.ui.theme.Radio_Btn_Green_Selected
import com.marco.lista_de_tarefa.ui.theme.Radio_Btn_Red_Disabled
import com.marco.lista_de_tarefa.ui.theme.Radio_Btn_Red_Selected
import com.marco.lista_de_tarefa.ui.theme.Radio_Btn_Yellow_Disabled
import com.marco.lista_de_tarefa.ui.theme.Radio_Btn_Yellow_Selected
import com.marco.lista_de_tarefa.verific.Constantes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalvarTarefa(
    navController: NavController
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val tarefasRepositorio = TarefasRepositorio()

    var tituloTarefa by rememberSaveable {
        mutableStateOf("")
    }

    var descricao by rememberSaveable {
        mutableStateOf("")
    }

    val semPrioridadeTarefa by rememberSaveable {
        mutableStateOf(false)
    }

    var baixaPrioridadeTarefa by rememberSaveable {
        mutableStateOf(false)
    }

    var mediaPrioridadeTarefa by rememberSaveable {
        mutableStateOf(false)
    }

    var altaPrioridadeTarefa by rememberSaveable {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = { MainTopBar(
            text = "Salvar Anotação",
        )}
    ){paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())){
            CaixaDeTexto(
                value = tituloTarefa,
                onValueChange = {
                                tituloTarefa = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 20.dp),
                label = "Título da Anotação",
                maxLine = 1,
                keyBoardType = KeyboardType.Text
            )
            CaixaDeTexto(
                value = descricao,
                onValueChange = {
                                descricao = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(20.dp, 20.dp, 20.dp, 0.dp),
                label = "Anotação",
                maxLine = 6,
                keyBoardType = KeyboardType.Text
            )
            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = "Nível de prioridade")
                RadioButton(
                    selected = baixaPrioridadeTarefa,
                    onClick = {
                        baixaPrioridadeTarefa = !baixaPrioridadeTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = Radio_Btn_Green_Disabled,
                        selectedColor = Radio_Btn_Green_Selected
                    )
                )

                RadioButton(
                    selected = mediaPrioridadeTarefa,
                    onClick = {
                        mediaPrioridadeTarefa = !mediaPrioridadeTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = Radio_Btn_Yellow_Disabled,
                        selectedColor = Radio_Btn_Yellow_Selected
                    )
                )

                RadioButton(
                    selected = altaPrioridadeTarefa,
                    onClick = {
                        altaPrioridadeTarefa = !altaPrioridadeTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = Radio_Btn_Red_Disabled,
                        selectedColor = Radio_Btn_Red_Selected
                    )
                )
            }
            Row (
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ){
                Btn(
                    onClick = {
                        var messagem: Boolean

                        scope.launch(Dispatchers.IO){
                            if (tituloTarefa.isEmpty()){
                                messagem = false
                            }
                            else if (tituloTarefa.isNotEmpty() && descricao.isNotEmpty() && baixaPrioridadeTarefa){
                                tarefasRepositorio.salvarTarefa(tituloTarefa, descricao, Constantes.PRIORIDADE_BAIXA)
                                messagem = true
                            }
                            else if (tituloTarefa.isNotEmpty() && descricao.isNotEmpty() && mediaPrioridadeTarefa){
                                tarefasRepositorio.salvarTarefa(tituloTarefa, descricao, Constantes.SEM_PRIORIDADE_MEDIA)
                                messagem = true
                            }
                            else if (tituloTarefa.isNotEmpty() && descricao.isNotEmpty() && altaPrioridadeTarefa){
                                tarefasRepositorio.salvarTarefa(tituloTarefa, descricao, Constantes.SEM_PRIORIDADE_ALTA)
                                messagem = true
                            }
                            else if (tituloTarefa.isNotEmpty() && descricao.isNotEmpty() && semPrioridadeTarefa){
                                tarefasRepositorio.salvarTarefa(tituloTarefa, descricao, Constantes.SEM_PRIORIDADE)
                                messagem = true
                            }
                            else if (tituloTarefa.isNotEmpty() && baixaPrioridadeTarefa){
                                tarefasRepositorio.salvarTarefa(tituloTarefa, descricao, Constantes.PRIORIDADE_BAIXA)
                                messagem = true
                            }
                            else if (tituloTarefa.isNotEmpty() && mediaPrioridadeTarefa){
                                tarefasRepositorio.salvarTarefa(tituloTarefa, descricao, Constantes.SEM_PRIORIDADE_MEDIA)
                                messagem = true
                            }
                            else if (tituloTarefa.isNotEmpty() && altaPrioridadeTarefa){
                                tarefasRepositorio.salvarTarefa(tituloTarefa, descricao, Constantes.SEM_PRIORIDADE_ALTA)
                                messagem = true
                            }
                            else {
                                tarefasRepositorio.salvarTarefa(tituloTarefa, descricao, Constantes.SEM_PRIORIDADE)
                                messagem = true
                            }


                        scope.launch ( Dispatchers.Main ){
                            if (messagem){
                                Toast.makeText(context, "Sucesso ao Salvar a Tarefa!", Toast.LENGTH_SHORT).show()
                                navController.popBackStack()
                            }
                            else{
                                Toast.makeText(context, "Título vazio!", Toast.LENGTH_SHORT).show()
                            }
                        }
                              }
                              },
                    modifier = Modifier
                        .width(300.dp)
                        .height(80.dp)
                        .padding(10.dp),
                    texto = "Salvar",
                )
            }
    }
    }
}



