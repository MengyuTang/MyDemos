package com.example.fpm0322.weatherapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fpm0322.myfirstapp.R
import kotlinx.android.synthetic.main.item_weather_info.view.*
import org.jetbrains.anko.toast

class ForecastListAdapter(private val items: List<Weather>): RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    lateinit var listener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_weather_info,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weathers: Weather = items[position]
        with(weathers){
            with(holder.view){
                tvDate.text = date
                tvWeek.text = week
                icon.setBackgroundResource(R.mipmap.ic_launcher_round)
                tvWeather.text = weather
                tvMaxTemperature.text = temperature
            }
            holder.view.setOnClickListener { listener.onItemClick(position) }
        }
    }

    class ViewHolder(var view:View): RecyclerView.ViewHolder(view)
}