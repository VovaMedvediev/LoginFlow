package com.example.loginflow

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.os.Looper
import androidx.lifecycle.Observer
import com.example.loginflow.login.LoginInteractor
import com.example.loginflow.login.LoginViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.LooperMode

@RunWith(RobolectricTestRunner::class)
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        viewModel = LoginViewModel(null, null)
    }

    @Test
    fun testIsEmailFormValid() {
        val valid = viewModel.isEmailFormValid("test@test.com")
        assertTrue(valid)
        val invalid = viewModel.isEmailFormValid("not an email address")
        assertFalse(invalid)
    }
}