package com.kritora.nearbytracker.domain.use_case

import com.kritora.nearbytracker.domain.model.Task
import com.kritora.nearbytracker.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: Int): Task? {
        return repository.getTaskById(id)
    }
}
