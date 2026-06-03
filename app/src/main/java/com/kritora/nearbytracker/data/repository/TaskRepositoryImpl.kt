package com.kritora.nearbytracker.data.repository

import com.kritora.nearbytracker.data.local.TaskDao
import com.kritora.nearbytracker.data.mapper.toTask
import com.kritora.nearbytracker.data.mapper.toTaskEntity
import com.kritora.nearbytracker.data.remote.TaskApi
import com.kritora.nearbytracker.domain.model.Task
import com.kritora.nearbytracker.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val api: TaskApi,
    private val dao: TaskDao
) : TaskRepository {

    override fun getTasks(): Flow<List<Task>> {
        return dao.getAllTasks().map { entities ->
            entities.map { it.toTask() }
        }
    }

    override suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)?.toTask()
    }

    override suspend fun addTask(task: Task) {
        dao.insertTask(task.toTaskEntity())
    }

    override suspend fun deleteTask(id: Int) {
        dao.deleteTaskById(id)
    }

    override suspend fun syncTasks(): Result<Unit> {
        return try {
            val remoteTasks = api.getTasks()
            dao.insertTasks(remoteTasks.map { it.toTaskEntity() })
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
