package com.example.logtestapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.logtestapplication.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.chromium.net.CronetEngine
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var appLog: AppLogModel = AppLogModel("${System.currentTimeMillis()}", actionsList = arrayListOf(ActivityAction.InitAction()))
    private lateinit var sharedPreferences: SharedPreferences
    private val LOG_DATA = "log_data"
    private lateinit var myBuilder: CronetEngine.Builder
    private lateinit var cronetEngine: CronetEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = this.getSharedPreferences("com.example.logtestapplication", 0)
        myBuilder = CronetEngine.Builder(this)
        cronetEngine = myBuilder.build()

        appLogModelToGson(appLog) {
            binding.tv.text = objectToJSON(it)
        }

        binding.btn1.setOnClickListener { // fetch from shared pref
            /*appLog.actionsList.add(ActivityAction.AddNewAction(data = UserModel(name = "btn1")))
            appLogModelToGson(appLog) {
                binding.tv.text = objectToJSON(it)
            }*/
            val data = sharedPreferences.getString(LOG_DATA, "null")
            binding.tv.text = data


            //todo: json to model
            //todo: convert string data to model data
        }

        binding.btn2.setOnClickListener { // save & clear from text view
            // clear shared pref values
            sharedPreferences.edit().clear().apply()
            binding.tv.text = ""
        }

        binding.btn3.setOnClickListener { //fetch data from shared pref
            appLog.actionsList.add(ActivityAction.DoneAction(data = UserModel(name = "Add user Action")))
            sharedPreferences.edit().putString(LOG_DATA, objectToJSON(appLog)).apply()
            val data = sharedPreferences.getString(LOG_DATA, "null")
            binding.tv.text = data
        }


    }

    private fun objectToJSON(raw: Any): String? {
        val gson = Gson()
        var data: String? = null
        try {
            data = gson.toJson(raw)
            Log.e("raw", raw as String)
            Log.e("jsonOutput", data)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return data
    }

    private fun jsonToObj(data: String, onDataReceived: (t: AppLogModel?) -> Unit): AppLogModel? {
        val gson = Gson()
        var modelData: AppLogModel? = null
        try {
            val type: Type = object : TypeToken<AppLogModel>() {}.type
            modelData = gson.fromJson(data, type)
            onDataReceived(modelData)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return modelData
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