package com.example.ztasks.data.models
data class Task(
    val completed: Boolean = false,
    val createdAt: String? = null,
    val description: String,
    val dueDate: Any? = null,
    val title: String,
    val updatedAt: String? = null,
    val userId: Int = 0
)
