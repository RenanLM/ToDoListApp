package br.com.renan.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.renan.todolistapp.data.AppDatabase
import br.com.renan.todolistapp.ui.TaskDetailScreen
import br.com.renan.todolistapp.ui.TaskListScreen
import br.com.renan.todolistapp.ui.theme.ToDoListAppTheme
import br.com.renan.todolistapp.viewmodel.TaskViewModel
import androidx.compose.foundation.layout.safeDrawingPadding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().safeDrawingPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Inicializa o banco de dados/DAO
                    val database = AppDatabase.getDatabase(applicationContext)
                    val taskDao = database.taskDao()

                    val viewModelFactory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return TaskViewModel(taskDao) as T
                        }
                    }

                    // Instancia a ViewModel
                    val taskViewModel: TaskViewModel = viewModel(factory = viewModelFactory)

                    // Configura o NavController
                    val navController = rememberNavController()

                    // Define as Rotas e o fluxo de telas (NavHost)
                    NavHost(navController = navController, startDestination = "taskList") {

                        // Rota da Tela 1: Lista
                        composable("taskList") {
                            TaskListScreen(
                                viewModel = taskViewModel,
                                onTaskClick = { taskId ->
                                    // Navega para a tela de detalhes passando o ID
                                    navController.navigate("taskDetail/$taskId")
                                }
                            )
                        }

                        // Rota da Tela 2: Detalhes
                        composable(
                            route = "taskDetail/{taskId}",
                            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            // Extrai o ID da rota ou usa 0 como fallback
                            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0

                            TaskDetailScreen(
                                taskId = taskId,
                                viewModel = taskViewModel,
                                onBackClick = {
                                    // Remove a tela atual da pilha e volta para a lista
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}