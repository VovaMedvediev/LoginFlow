package com.example.loginflow.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.loginflow.home.HomeActivity
import com.example.loginflow.R
import com.example.loginflow.SessionManager
import com.example.loginflow.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(
            this,
            LoginViewModel.LoginViewModelFactory(LoginInteractor(), SessionManager(baseContext))
        )[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.apply {
            lifecycleOwner = this@LoginActivity
            viewModel = loginViewModel
        }

        loginViewModel.loginState.observe(::getLifecycle, ::updateUI)

        setupViews()
    }

    private fun updateUI(screenState: LoginState?) {
        when (screenState) {
            LoginState.LOADING -> loginButton.startAnimation()
            LoginState.SUCCESS -> loginButton.revertAnimation {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
    }

    private fun setupViews() {
        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {
                if (loginViewModel.isEmailValid.value != true) {
                    emailTextInputLayout.error = getString(R.string.email_error)
                } else {
                    emailTextInputLayout.error = null
                }
            }
        })
        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {
                if (loginViewModel.isPasswordValid.value != true) {
                    passwordTextInputLayout.error = getString(R.string.password_error)
                } else {
                    passwordTextInputLayout.error = null
                }
            }
        })
        loginButton.setOnClickListener { loginViewModel.onLoginClicked() }
    }
}
