package com.example.fpm0322.weatherapp

import android.util.Log
import com.google.gson.Gson

interface Command<T> {
    fun execute(): T
}

class RequestCommand(var url: String): Command<ResultData>{
    override fun execute(): ResultData {
        val resultData = Request(url).execute()
        Log.e(javaClass.simpleName,"resultData:"+resultData)
        var data: ResultData = resultData.result
        Log.e(javaClass.simpleName,"resultData:"+data.toString())
        return data
    }
}