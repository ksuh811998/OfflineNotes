package com.kritora.nearbytracker.presentation.add_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritora.nearbytracker.domain.model.Priority
import com.kritora.nearbytracker.domain.model.Task
import com.kritora.nearbytracker.domain.use_case.AddTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onSaveTask(title: String, description: String, priority: Priority) {
        viewModelScope.launch {
            if (title.isBlank()) {
                _eventFlow.emit(UiEvent.ShowSnackbar("Title cannot be empty"))
                return@launch
            }
            val task = Task(
                id = Random.nextInt(),
                title = title,
                description = description,
                priority = priority,
                timestamp = System.currentTimeMillis(),
                isCompleted = false
            )
            addTaskUseCase(task)
            _eventFlow.emit(UiEvent.SaveTask)
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveTask: UiEvent()
    }
}
