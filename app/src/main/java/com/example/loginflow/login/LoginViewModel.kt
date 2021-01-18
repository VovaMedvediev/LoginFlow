package com.example.loginflow.login

import android.util.Patterns
import androidx.lifecycle.*
import com.example.loginflow.SessionManager

class LoginViewModel(
    private val loginInteractor: LoginInteractor?,
    private val sessionManager: SessionManager?
) : ViewModel(),
    LoginInteractor.OnLoginFinishedListener {
    val email = MutableLiveData("")
    val isEmailValid = MediatorLiveData<Boolean>().apply {
        addSource(email) {
            val valid = isEmailFormValid(it)
            value = valid
        }
    }
    val password = MutableLiveData("")
    val isPasswordValid = MediatorLiveData<Boolean>().apply {
        addSource(password) {
            val valid = isPasswordFormValid(it)
            value = valid
        }
    }
    val loginState: LiveData<LoginState>
        get() = _loginState
    private val _loginState: MutableLiveData<LoginState> = MutableLiveData()

    fun onLoginClicked() {
        _loginState.value = LoginState.LOADING
        loginInteractor?.login(this)
    }

    override fun onSuccess() {
        sessionManager?.saveSession(true)
        _loginState.value = LoginState.SUCCESS
    }

    fun isEmailFormValid(email: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isPasswordFormValid(password: String) = password.length >= 4

    class LoginViewModelFactory(
        private val loginInteractor: LoginInteractor,
        private val sessionManager: SessionManager,
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(loginInteractor, sessionManager) as T
        }
    }
}
