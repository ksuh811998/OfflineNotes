package com.kritora.nearbytracker.domain.use_case

import com.kritora.nearbytracker.domain.repository.TaskRepository
import javax.inject.Inject

class SyncTasksUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.syncTasks()
    }
}
