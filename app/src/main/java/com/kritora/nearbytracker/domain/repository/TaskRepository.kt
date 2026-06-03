package com.kritora.nearbytracker.domain.repository

import com.kritora.nearbytracker.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(): Flow<List<Task>>
    suspend fun getTaskById(id: Int): Task?
    suspend fun addTask(task: Task)
    suspend fun deleteTask(id: Int)
    suspend fun syncTasks(): Result<Unit>
}
