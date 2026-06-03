package com.kritora.nearbytracker.presentation.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritora.nearbytracker.domain.use_case.DeleteTaskUseCase
import com.kritora.nearbytracker.domain.use_case.GetTasksUseCase
import com.kritora.nearbytracker.domain.use_case.SyncTasksUseCase
import com.kritora.nearbytracker.util.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val syncTasksUseCase: SyncTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    private val _uiState = MutableStateFlow<TaskListUiState>(TaskListUiState.Loading)
    val uiState: StateFlow<TaskListUiState> = _uiState.asStateFlow()

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val connectivity = connectivityObserver.observe()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ConnectivityObserver.Status.Unavailable)

    init {
        combine(getTasksUseCase(), connectivity) { tasks, status ->
            TaskListUiState.Success(
                tasks = tasks,
                isOnline = status == ConnectivityObserver.Status.Available
            )
        }.onEach { state ->
            _uiState.value = state
        }.catch { e ->
            _uiState.value = TaskListUiState.Error(e.message ?: "Unknown error")
        }.launchIn(viewModelScope)
    }

    fun syncTasks() {
        viewModelScope.launch {
            _isSyncing.value = true
            val result = syncTasksUseCase()
            _isSyncing.value = false
            if (result.isSuccess) {
                _eventFlow.emit(UiEvent.ShowSnackbar("Sync completed successfully"))
            } else {
                _eventFlow.emit(UiEvent.ShowSnackbar("Sync failed: ${result.exceptionOrNull()?.message}"))
            }
        }
    }

    fun onDeleteTask(id: Int) {
        viewModelScope.launch {
            deleteTaskUseCase(id)
            _eventFlow.emit(UiEvent.ShowSnackbar("Task deleted"))
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
    }
}
