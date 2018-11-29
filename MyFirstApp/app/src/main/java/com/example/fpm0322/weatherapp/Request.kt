package com.example.fpm0322.weatherapp

import android.util.Log
import com.google.gson.Gson
import java.net.URL

 class Request(val url: String){
     fun execute(): ResponseData{
        var forecastJsonStr = URL(url).readText()
         var data: ResponseData = Gson().fromJson(forecastJsonStr,ResponseData::class.java)
         Log.e(javaClass.simpleName,data.toString())
         return data
    }
}