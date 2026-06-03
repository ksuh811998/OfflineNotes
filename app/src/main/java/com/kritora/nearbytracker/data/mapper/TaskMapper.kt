package com.kritora.nearbytracker.data.mapper

import com.kritora.nearbytracker.data.local.entity.TaskEntity
import com.kritora.nearbytracker.data.remote.dto.TaskDto
import com.kritora.nearbytracker.domain.model.Priority
import com.kritora.nearbytracker.domain.model.Task

private val englishTitles = listOf(
    "Buy groceries for the week",
    "Complete the project presentation",
    "Call the bank for mortgage info",
    "Morning 5km run",
    "Pay the monthly internet bill",
    "Read 20 pages of a new book",
    "Take the dog to the park",
    "Weekly team sync meeting",
    "Clean and organize the kitchen",
    "Schedule an annual checkup",
    "Update system software",
    "Cook a healthy dinner",
    "Fix the leaking kitchen faucet",
    "Organize the home office",
    "Plan the summer vacation",
    "Wash the car",
    "Water the indoor plants",
    "Send feedback to the developer",
    "Check mail from the post office",
    "Buy a gift for John's birthday"
)

fun TaskDto.toTaskEntity(): TaskEntity {
    // Select an English title based on the API ID
    val englishTitle = englishTitles[id % englishTitles.size]
    
    return TaskEntity(
        id = id,
        title = englishTitle,
        description = "Task automatically synced from the cloud server.",
        priority = if (completed) Priority.LOW else Priority.HIGH,
        timestamp = System.currentTimeMillis(),
        isCompleted = completed
    )
}

fun TaskEntity.toTask(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        priority = priority,
        timestamp = timestamp,
        isCompleted = isCompleted
    )
}

fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        priority = priority,
        timestamp = timestamp,
        isCompleted = isCompleted
    )
}
