package com.marco.lista_de_tarefa.itemLista

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.marco.lista_de_tarefa.model.Tarefa
import com.marco.lista_de_tarefa.repositore.TarefasRepositorio
import com.marco.lista_de_tarefa.ui.theme.Radio_Btn_Red_Selected
import com.marco.lista_de_tarefa.ui.theme.Radio_Btn_Yellow_Selected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TarefaItem(
    position: Int,
    listaTarefas: MutableList<Tarefa>,
    context: Context,
    navController: NavController
) {
    val tituloTarefa = listaTarefas[position].tarefa
    val descricaoTarefa = listaTarefas[position].descricao
    val prioridade = listaTarefas[position].prioridade

    val scope = rememberCoroutineScope()
    val tarefasRepositorio = TarefasRepositorio()


    fun dialog() {

        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deletar Tarefa")
            .setMessage("Deseja Deletar a Tarefa")
            .setPositiveButton("Sim"){_,_->
                tarefasRepositorio.deletarTarefa(tituloTarefa.toString())
                scope.launch (Dispatchers.Main){
                    listaTarefas.removeAt(position)
                    navController.navigate("listaTarefa")
                    Toast.makeText(context, "Sucesso ao Deletar Tarefa", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Não"){_,_->

            }
            .show()
    }

    val nivelPrioridade: String = when(prioridade){
        0 -> {
            "Sem Prioridade"
        }
        1 -> {
            "Prioridade Baixa"
        }
        2 -> {
            "Prioridade Média"
        }
        else -> {
            "Prioridade Alta"
        }
    }

    val color= when(prioridade){
        0 -> {
            Color.Black
        }
        1 -> {
            Radio_Btn_Red_Selected
        }
        2 -> {
            Radio_Btn_Yellow_Selected
        }
        else -> {
            Radio_Btn_Red_Selected
        }
    }

    ElevatedCard(
        colors = CardDefaults.cardColors(

        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ){
            val (txtTitulo, txtDescricao, cardPrioridade, txtPrioridade, btDeletar) = createRefs()

            Text(
                text = tituloTarefa.toString(),
                modifier = Modifier.constrainAs(txtTitulo){
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = descricaoTarefa.toString(),
                modifier = Modifier.constrainAs(txtDescricao){
                    top.linkTo(txtTitulo.bottom, margin = 15.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = nivelPrioridade,
                modifier = Modifier.constrainAs(txtPrioridade){
                    top.linkTo(txtDescricao.bottom, margin = 15.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )

            Card(
                colors = CardDefaults.cardColors(
                    color
                ),
                modifier = Modifier
                    .size(20.dp)
                    .constrainAs(cardPrioridade) {
                        top.linkTo(txtDescricao.bottom, margin = 16.dp)
                        start.linkTo(txtPrioridade.end, margin = 15.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    },
                shape = CircleShape
            ){

            }

            IconButton(
                onClick = {
                          dialog()
                          },
                modifier = Modifier.constrainAs(btDeletar){
                    top.linkTo(txtDescricao.bottom, margin = 10.dp)
                    start.linkTo(cardPrioridade.end, margin = 90.dp)
                }
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.Red)
            }

        }
    }
}

