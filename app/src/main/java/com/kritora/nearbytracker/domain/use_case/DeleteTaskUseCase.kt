package com.kritora.nearbytracker.domain.use_case

import com.kritora.nearbytracker.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteTask(id)
    }
}
