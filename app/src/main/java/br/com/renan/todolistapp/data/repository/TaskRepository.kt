package br.com.renan.todolistapp.data.repository

import br.com.renan.todolistapp.data.local.TaskDao
import br.com.renan.todolistapp.data.local.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    fun getAllTasks(): Flow<List<TaskEntity>> = taskDao.getAllTasks()

    suspend fun addTask(title: String, description: String) {
        taskDao.insert(TaskEntity(title = title, description = description))
    }
}
