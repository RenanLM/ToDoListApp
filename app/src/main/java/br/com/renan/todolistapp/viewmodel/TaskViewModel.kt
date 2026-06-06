package br.com.renan.todolistapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.renan.todolistapp.data.Task
import br.com.renan.todolistapp.data.TaskDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine


enum class TaskFilter {
    ALL,
    COMPLETED,
    PENDING
}

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {

    val searchQuery = MutableStateFlow("")
    val currentFilter = MutableStateFlow(TaskFilter.ALL)

    private val _allTasks = taskDao.getAllTasks()

    val tasks: StateFlow<List<Task>> = combine(
        _allTasks,
        searchQuery,
        currentFilter
    ) { taskList, query, filter ->
        taskList.filter { task ->
            // Verifica se o título ou a descrição contêm o texto pesquisado
            val matchesQuery = task.title.contains(query, ignoreCase = true) ||
                    task.description.contains(query, ignoreCase = true)

            // Verifica se a tarefa atende ao filtro de status
            val matchesFilter = when (filter) {
                TaskFilter.ALL -> true
                TaskFilter.PENDING -> !task.isCompleted
                TaskFilter.COMPLETED -> task.isCompleted
            }

            matchesQuery && matchesFilter
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // Funções para a interface atualizar os estados de pesquisa e filtro
    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun updateFilter(filter: TaskFilter) {
        currentFilter.value = filter
    }

    fun insertTask(task: Task) {
        viewModelScope.launch {
            taskDao.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskDao.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.deleteTask(task)
        }
    }

    // Retorna os dados de uma tarefa específica para a tela de Detalhes
    fun getTaskById(taskId: Int) = taskDao.getTaskById(taskId)
}