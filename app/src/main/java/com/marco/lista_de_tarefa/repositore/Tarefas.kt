package com.marco.lista_de_tarefa.repositore
import com.marco.lista_de_tarefa.datasource.DataSource
import com.marco.lista_de_tarefa.model.Tarefa


class TarefasRepositorio{

    private val dataSource = DataSource()

    fun salvarTarefa(
        tarefa: String,
        descricao: String,
        prioridade: Int){
        dataSource.salvarTarefa(tarefa,descricao,prioridade)
    }

    fun recuperarTarefa(): kotlinx.coroutines.flow.Flow<MutableList<Tarefa>> {
        return dataSource.recuperar()
    }

    fun deletarTarefa(tarefa: String){
        dataSource.deletar(tarefa)
    }
}