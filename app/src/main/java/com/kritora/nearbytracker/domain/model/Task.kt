package com.kritora.nearbytracker.domain.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Priority,
    val timestamp: Long,
    val isCompleted: Boolean
)

enum class Priority {
    LOW, MEDIUM, HIGH
}
