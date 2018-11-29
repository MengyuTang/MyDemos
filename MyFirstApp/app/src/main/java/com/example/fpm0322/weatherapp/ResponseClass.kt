package com.example.fpm0322.weatherapp

import java.io.Serializable

data class ResponseData(val resultcode: String,val reason: String,val result: ResultData,val error_code: Int )

data class ResultData(val sk: Any,val today: Weather, val future:List<Weather>)

data class Weather(val date: String, val week: String, val wind: String, val weather: String, val temperature: String):Serializable