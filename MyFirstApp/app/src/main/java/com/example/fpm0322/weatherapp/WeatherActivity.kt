package com.example.fpm0322.weatherapp
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.fpm0322.myfirstapp.R
import kotlinx.android.synthetic.main.activity_weather.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class WeatherActivity : AppCompatActivity() {

    private var url: String  = "http://v.juhe.cn/weather/index?format=2&cityname=%E8%8B%8F%E5%B7%9E&key=712663d0c2736690a9a70f716a381004"

    var datas: List<Weather>? = null

    var currentActivity: WeatherActivity?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        initView()
    }

     fun initView(){
         currentActivity = this
        forecastList.layoutManager = LinearLayoutManager(this)
         getForecastInfo()
    }

    /**
     * 使用Anko提供的异步方法获取网络数据
     */
    private fun getForecastInfo(){
        async {
            var result = RequestCommand(url).execute()
            uiThread {
                Log.e(javaClass.simpleName,"result:"+result.future)
                var weathers: List<Weather> = result.future
                datas = weathers
                var adapter = ForecastListAdapter(weathers)
                adapter.setOnItemClickListener(ItemClick())
                forecastList.adapter = adapter
            }
        }
    }

    /**
     * 定义一个内部类实现click接口，并处理click事件（此处相当于java中click事件的回调）
     */
    inner class ItemClick: ForecastListAdapter.OnItemClickListener {
        override fun onItemClick(position: Int){
            Log.e(javaClass.simpleName,"click position:"+position)
            if (datas?.size!!>0){
                Log.e(javaClass.simpleName,"click position:"+datas!![position])
                intent.putExtra("weather", datas!![position])
                intent.setClass(currentActivity,DetailActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
