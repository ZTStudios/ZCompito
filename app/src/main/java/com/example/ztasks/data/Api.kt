package com.example.ztasks.data

import com.example.ztasks.data.models.LoginRequest
import com.example.ztasks.data.models.LoginResponse
import com.example.ztasks.data.models.Task
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @GET("tasks/{id}")
    suspend fun getUserTasks(
        @Path("id") id: Int
    ): List<Task>

    @POST("auth")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    object RetrofitTaskFactory {
        fun makeRetrofitService(): Api {
            return Retrofit.Builder()
                .baseUrl("https://zcompito-api.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Api::class.java)
        }
    }

}