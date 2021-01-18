package com.example.loginflow

import android.content.Context

class SessionManager(private val context: Context) {
    companion object {
        private const val PREF_STORAGE = "pref_storage"
        private const val PREF_SESSION_KEY = "pref_session_key"
    }

    fun saveSession(isLoggedIn: Boolean) {
        val sharedPref = context.getSharedPreferences(PREF_STORAGE, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean(PREF_SESSION_KEY, isLoggedIn)
            commit()
        }
    }

    fun checkSession(): Boolean {
        val sharedPref = context.getSharedPreferences(PREF_STORAGE, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(PREF_SESSION_KEY, false)
    }
}