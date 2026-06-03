package com.kritora.nearbytracker.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TaskDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("completed") val completed: Boolean,
    @SerializedName("userId") val userId: Int
)
