package com.example.ztasks.data.models

data class LoginResponse(
    val createdAt: String,
    val email: String,
    val id: Int,
    val name: String,
    val password: String
)