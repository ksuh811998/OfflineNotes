package com.kritora.nearbytracker.domain.use_case

import com.kritora.nearbytracker.domain.model.Task
import com.kritora.nearbytracker.domain.repository.TaskRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        repository.addTask(task)
    }
}
