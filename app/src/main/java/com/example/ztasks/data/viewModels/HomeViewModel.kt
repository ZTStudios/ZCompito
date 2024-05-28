package com.example.ztasks.data.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ztasks.data.Api
import com.example.ztasks.data.models.LoginRequest
import com.example.ztasks.data.models.Task
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val apiService = Api.RetrofitTaskFactory.makeRetrofitService()

    private val _userTasks = MutableLiveData<List<Task>>()
    val userTasks: LiveData<List<Task>> = _userTasks

    fun fetchUserTasks(userId: Int) {
        viewModelScope.launch {
            try {
                val tasks = apiService.getUserTasks(userId)
                _userTasks.value = tasks
            } catch (e: Exception) {
                _userTasks.value = emptyList()
            }
        }
    }
}