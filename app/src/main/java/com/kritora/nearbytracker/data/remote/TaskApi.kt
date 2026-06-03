package com.kritora.nearbytracker.data.remote

import com.kritora.nearbytracker.data.remote.dto.TaskDto
import retrofit2.http.GET

interface TaskApi {
    @GET("todos")
    suspend fun getTasks(): List<TaskDto>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}
