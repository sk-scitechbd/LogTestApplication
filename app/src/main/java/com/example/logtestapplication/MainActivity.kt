package com.example.logtestapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.logtestapplication.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var appLog: AppLogModel = AppLogModel("${System.currentTimeMillis()}", actionsList = arrayListOf(ActivityAction.InitAction()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appLogModelToGson(appLog) {
            binding.tv.text = JsonFormatterString.formatString(it)
        }
        binding.btn1.setOnClickListener {
        }
        binding.btn2.setOnClickListener { }
        binding.btn3.setOnClickListener { }


    }


    private inline fun appLogModelToGson(dataModel: AppLogModel?, setData: (t: String) -> Unit) {
        var data = "{\"null\":\"null\"}"
        dataModel?.let { appLogs ->
            val gson = Gson()
            data = gson.toJson(appLogs)
        }
        setData(data)
    }


}