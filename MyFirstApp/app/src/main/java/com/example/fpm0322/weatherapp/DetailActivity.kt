package com.example.fpm0322.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.fpm0322.myfirstapp.R
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.backgroundResource

class DetailActivity : AppCompatActivity() {

    var weather: Weather?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initView()
    }

    fun initView(){
        weather = intent.getSerializableExtra("weather") as Weather
        icon.backgroundResource = R.mipmap.ic_launcher_round
        weatherDescription.text = weather?.weather
        tvDate.text = weather?.date
        maxTemperature.text = weather?.temperature
    }
}
