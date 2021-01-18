package com.example.loginflow.login

import com.example.loginflow.postDelayed

class LoginInteractor {
    companion object {
        private const val TWO_SECONDS_DELAY = 2000L
    }

    interface OnLoginFinishedListener {
        fun onSuccess()
    }

    fun login(listener: OnLoginFinishedListener) {
        postDelayed(TWO_SECONDS_DELAY) {
            listener.onSuccess()
        }
    }
}