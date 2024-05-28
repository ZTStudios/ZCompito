// TaskViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ztasks.data.Api
import com.example.ztasks.data.models.Task
import kotlinx.coroutines.launch
import retrofit2.Response

class TaskViewModel : ViewModel() {
    private val apiService = Api.RetrofitTaskFactory.makeRetrofitService()

    fun createTask(task: Task, userId: Int, onResult: (Boolean, Task?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.createTask(task, userId)
                println(response)
                println(response.body())
                if(response.isSuccessful) {
                    val createdTask = response.body()
                    onResult(true, createdTask)
                } else {
                    onResult(false, null)
                }
            } catch (e: Exception) {
                onResult(false, null)
            }
        }
    }

    fun updateTask(id: Int, task: Task, onResult: (Boolean, Task?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.updateTask(id, task)
                if(response.isSuccessful) {
                    val updatedTask = response.body()
                    onResult(true, updatedTask)
                } else {
                    onResult(false, null)
                }
            } catch (e: Exception) {
                onResult(false, null)
            }
        }
    }

    fun deleteTask(id: Int, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.deleteTask(id)
                if(response.isSuccessful) {
                    onResult(true)
                } else {
                    onResult(false)
                }
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}
