package com.darthside.marvelissimo.api

import android.util.Log
import com.darthside.marvelissimo.models.character.CharacterDTO
import com.darthside.marvelissimo.models.series.SeriesDTO
import com.darthside.marvelissimo.models.character.CharacterDataWrapper
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
    private val characterName = "spider-man"

    fun getCharacterCall(callback: (CharacterDTO) -> Unit, characterName : String) {

        url = "https://gateway.marvel.com/v1/public/characters?name=$characterName&ts=$ts&apikey=$apiKey&hash=$hash"
        Log.d(httpTag, "Attempting request")
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val response = response.body()?.string()
                println("CharacterDataWrapper body: $response")

                val gson = GsonBuilder().create()
                val jsonData = gson.fromJson(response, CharacterDataWrapper::class.java)

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

    fun getSeriesCall(callback: (SeriesDTO) -> Unit, seriesName : String) {

        url = "https://gateway.marvel.com/v1/public/series?titleStartsWith=$seriesName&ts=$ts&apikey=$apiKey&hash=$hash"
        Log.d(httpTag, "Attempting request")
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val response = response.body()?.string()
                println("CharacterDataWrapper body: $response")

                val gson = GsonBuilder().create()
                val jsonData = gson.fromJson(response, CharacterDataWrapper::class.java)

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

    fun getAllCharactersCall() {

    }

    fun getAllSeriesCall() {

    }
}