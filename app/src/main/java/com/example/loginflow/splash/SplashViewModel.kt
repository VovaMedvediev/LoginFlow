package com.example.loginflow.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginflow.SessionManager

class SplashViewModel(sessionManager: SessionManager) : ViewModel() {
    val isSessionActive = sessionManager.checkSession()

    class SplashViewModelFactory(
        private val sessionManager: SessionManager,
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SplashViewModel(sessionManager) as T
        }
    }
}