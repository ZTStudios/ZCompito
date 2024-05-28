package com.example.ztasks.data

import com.example.ztasks.data.models.Task
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("tasks")
    suspend fun getUserTasks(
        @Query("id") id: Int
    ): List<Task>

    companion object {
        const val BASE_URL = "https://api.example.com/"
    }
}