package com.example.fpm0322.weatherapp

import android.app.Application
import kotlin.properties.Delegates

class MyApplication:Application() {
    companion object{
        private var instance: MyApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}