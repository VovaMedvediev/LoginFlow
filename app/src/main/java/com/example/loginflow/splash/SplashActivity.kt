package com.example.loginflow.splash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.loginflow.R
import com.example.loginflow.SessionManager
import com.example.loginflow.home.HomeActivity
import com.example.loginflow.login.LoginActivity
import com.example.loginflow.postDelayed

class SplashActivity : AppCompatActivity() {
    companion object {
        private const val TWO_SECONDS_DELAY = 2000L
    }
    private val splashViewModel: SplashViewModel by lazy {
        ViewModelProvider(
            this,
            SplashViewModel.SplashViewModelFactory(SessionManager(baseContext))
        )[SplashViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        postDelayed(TWO_SECONDS_DELAY) {
            if (splashViewModel.isSessionActive) {
                navigateTo(HomeActivity::class.java)
            } else {
                navigateTo(LoginActivity::class.java)
            }
        }
    }

    private fun navigateTo(activity: Class<out Activity>) {
        startActivity(Intent(this, activity))
        finish()
    }
}