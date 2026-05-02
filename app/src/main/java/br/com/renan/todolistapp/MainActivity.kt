package br.com.renan.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import br.com.renan.todolistapp.data.local.AppDatabase
import br.com.renan.todolistapp.data.repository.TaskRepository
import br.com.renan.todolistapp.ui.task.TaskListScreen
import br.com.renan.todolistapp.ui.theme.ToDoListAppTheme
import br.com.renan.todolistapp.viewmodel.TaskViewModel
import br.com.renan.todolistapp.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "todo_database",
        ).build()
        val repository = TaskRepository(database.taskDao())

        setContent {
            ToDoListAppTheme {
                val viewModel: TaskViewModel = viewModel(
                    factory = TaskViewModelFactory(repository),
                )
                val tasks by viewModel.tasks.collectAsStateWithLifecycle()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaskListScreen(
                        tasks = tasks,
                        onSaveTask = viewModel::saveTask,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
