package com.darthside.marvelissimo.api

import android.util.Log
import com.darthside.marvelissimo.models.Character
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class APICaller {

    private val httpTag = "HTTP"
    private val ts = "1"
    private val apiKey = "174943a97b8c08a00a80d1ed425d9ed1"
    private val hash = "8b36d2a14cd3a4cec60c30e9f70b8ab3"
    private var url = ""
    private val client = OkHttpClient()
    private val inputName = "spider-man"

    fun getCharacterCall(callback: (Character) -> Unit, nameInput : String) {

        url = "https://gateway.marvel.com/v1/public/characters?name=$nameInput&ts=$ts&apikey=$apiKey&hash=$hash"
        Log.d(httpTag, "Attempting request")
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val response = response.body()?.string()
                println("Response body: $response")

                val gson = GsonBuilder().create()
                val jsonData = gson.fromJson(response, com.darthside.marvelissimo.models.Response::class.java)

                if (jsonData.data.results.isNotEmpty()) {
                    val character = jsonData.data.results.first()
                    callback(character)
                }
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

    fun getSeriesCall() {

    }

    fun getAllCharactersCall() {

    }

    fun getAllSeriesCall() {

    }
}