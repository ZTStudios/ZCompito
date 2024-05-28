package com.example.ztasks.data.models

data class Task(
    val completed: Boolean,
    val createdAt: String,
    val description: String,
    val dueDate: Any,
    val id: Int,
    val title: String,
    val updatedAt: String,
    val userId: Int
)