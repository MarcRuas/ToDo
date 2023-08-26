package com.marco.lista_de_tarefa.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.marco.lista_de_tarefa.componentes.MainFloat
import com.marco.lista_de_tarefa.componentes.MainTopBar
import com.marco.lista_de_tarefa.itemLista.TarefaItem
import com.marco.lista_de_tarefa.repositore.TarefasRepositorio



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTarefa(navController: NavController) {

    val tarefasRepositorio = TarefasRepositorio()
    val context = LocalContext.current

    Scaffold(
        topBar = { MainTopBar("Lista De Tarefa")},
        floatingActionButton = { MainFloat(navController)},
    ){paddingValues -> Column(Modifier.padding(paddingValues)){

        val listaTarefas = tarefasRepositorio.recuperarTarefa().collectAsState(mutableListOf()).value

        LazyColumn{
            itemsIndexed(listaTarefas){position, _->

                TarefaItem(position = position, listaTarefas = listaTarefas, context = context, navController = navController)

            }
        }
    }
    }
}



