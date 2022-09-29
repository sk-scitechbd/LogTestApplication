package com.example.logtestapplication

import android.os.Bundle
import android.util.Log
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
            binding.tv.text = objectToJSON(it)
        }

        binding.btn1.setOnClickListener {
            appLog.actionsList.add(ActivityAction.AddNewAction(data = UserModel(name = "btn1")))
            appLogModelToGson(appLog) {
                binding.tv.text = objectToJSON(it)
            }
        }
        binding.btn2.setOnClickListener {
            appLog.actionsList.add(ActivityAction.SwapAction(data = UserModel(name = "btn2")))
            appLogModelToGson(appLog) {
                binding.tv.text = objectToJSON(it)
            }
        }
        binding.btn3.setOnClickListener {
            ActivityAction.DeleteAction()
            appLog.actionsList.add(ActivityAction.DeleteAction(data = UserModel(name = "btn3")))
            appLogModelToGson(appLog) {
                binding.tv.text = objectToJSON(it)
            }
        }


    }

    private fun objectToJSON(raw: Any): String? {
        val gson = Gson()
        val data = gson.toJson(raw)
        Log.e("raw", raw as String)
        Log.e("jsonOutput", data)
        return data
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