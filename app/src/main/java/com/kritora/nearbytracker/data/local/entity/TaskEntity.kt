package com.kritora.nearbytracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kritora.nearbytracker.domain.model.Priority

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val priority: Priority,
    val timestamp: Long,
    val isCompleted: Boolean
)
