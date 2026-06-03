package com.kritora.nearbytracker.domain.use_case

import com.kritora.nearbytracker.domain.model.Task
import com.kritora.nearbytracker.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(): Flow<List<Task>> {
        return repository.getTasks()
    }
}
