package com.marco.lista_de_tarefa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marco.lista_de_tarefa.ui.theme.ListadeTarefaTheme
import com.marco.lista_de_tarefa.view.ListaTarefa
import com.marco.lista_de_tarefa.view.SalvarTarefa

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            ListadeTarefaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "ListaTarefa"){
                        composable(
                            route = "ListaTarefa"
                        ){
                            ListaTarefa(navController)
                        }
                        composable(
                            route = "SalvarTarefa"
                        ){
                            SalvarTarefa(navController)
                        }
                    }
                }
            }
        }
    }
}

