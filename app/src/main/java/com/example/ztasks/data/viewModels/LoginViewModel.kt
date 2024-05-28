import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ztasks.data.Api
import com.example.ztasks.data.models.LoginRequest
import com.example.ztasks.data.models.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val apiService = Api.RetrofitTaskFactory.makeRetrofitService()

    var loginResponse: Response<LoginResponse>? = null
        private set

    fun login(email: String, password: String, onResult: (Boolean, LoginResponse?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.login(LoginRequest(email, password))
                if(response.isSuccessful){
                    val userData = response.body()
                    onResult(true, userData)
                } else {
                    onResult(false, null)
                }
            } catch (e: Exception) {
                onResult(false, null)
            }
        }
    }
}


