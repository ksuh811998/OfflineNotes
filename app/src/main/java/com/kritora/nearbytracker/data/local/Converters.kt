package com.kritora.nearbytracker.data.local

import androidx.room.TypeConverter
import com.kritora.nearbytracker.domain.model.Priority

class Converters {
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}
