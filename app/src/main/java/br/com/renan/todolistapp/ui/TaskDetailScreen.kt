package br.com.renan.todolistapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.renan.todolistapp.viewmodel.TaskViewModel

@Composable
fun TaskDetailScreen(
    taskId: Int,
    viewModel: TaskViewModel,
    onBackClick: () -> Unit
) {
    // Coleta a tarefa original do banco
    val task by viewModel.getTaskById(taskId).collectAsState(initial = null)

    // Estados locais para controlar o que está sendo digitado
    var titleState by remember { mutableStateOf("") }
    var descriptionState by remember { mutableStateOf("") }

    // Quando a tarefa carrega do banco, preenche os campos de texto
    LaunchedEffect(task) {
        task?.let {
            titleState = it.title
            descriptionState = it.description
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Edit Task",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        if (task != null) {
            // Campo editável do Título
            OutlinedTextField(
                value = titleState,
                onValueChange = { titleState = it },
                label = { Text("Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Campo editável da Descrição
            OutlinedTextField(
                value = descriptionState,
                onValueChange = { descriptionState = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                minLines = 4
            )

            // Linha com os botões Salvar e Deletar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Botão Salvar
                Button(
                    onClick = {
                        task?.let {
                            // Salva no Room usando a ViewModel e volta pra lista
                            viewModel.updateTask(it.copy(title = titleState, description = descriptionState))
                            onBackClick()
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Save")
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Botão Deletar
                Button(
                    onClick = {
                        task?.let {
                            viewModel.deleteTask(it)
                            onBackClick()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Delete")
                }
            }
        } else {
            Text("Loading task details...")
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botão Cancelar
        OutlinedButton(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}