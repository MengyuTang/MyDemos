package com.example.fpm0322.weatherapp

import android.content.Context
import android.widget.Toast

/**
 * 提示
 */
fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}