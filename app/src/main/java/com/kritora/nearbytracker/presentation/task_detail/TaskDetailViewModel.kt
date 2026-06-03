package com.kritora.nearbytracker.presentation.task_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritora.nearbytracker.domain.model.Task
import com.kritora.nearbytracker.domain.use_case.GetTaskByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _task = MutableStateFlow<Task?>(null)
    val task = _task.asStateFlow()

    init {
        savedStateHandle.get<Int>("taskId")?.let { taskId ->
            viewModelScope.launch {
                _task.value = getTaskByIdUseCase(taskId)
            }
        }
    }
}
