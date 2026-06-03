package com.kritora.nearbytracker.presentation.task_list

import com.kritora.nearbytracker.domain.model.Task

sealed class TaskListUiState {
    object Loading : TaskListUiState()
    data class Success(val tasks: List<Task>, val isOnline: Boolean) : TaskListUiState()
    data class Error(val message: String) : TaskListUiState()
}
