package com.example.loginflow

import android.os.Handler
import android.os.Looper

fun postDelayed(delayMillis: Long, task: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(task, delayMillis)
}