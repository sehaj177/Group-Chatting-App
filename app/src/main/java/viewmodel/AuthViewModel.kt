package viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbot.Injection
import com.google.firebase.auth.FirebaseAuth
import data.UserRepository
import kotlinx.coroutines.launch
import data.Result
class AuthViewModel:ViewModel() {
    private val userRepository: UserRepository


    init{
        userRepository= UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
    }

    private val _authResult =MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get()=_authResult

    fun signUp(email: String, password: String, firstname: String, lastname: String){
        viewModelScope.launch {
            _authResult.value= userRepository.signUp(email,password,firstname,lastname)
        }
    }

    fun login(email: String, password: String){
        viewModelScope.launch {
            _authResult.value =userRepository.login(email,password)
        }
    }

}