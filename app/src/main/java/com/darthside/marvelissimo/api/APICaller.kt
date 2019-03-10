package com.darthside.marvelissimo.api

import android.util.Log
import com.darthside.marvelissimo.models.character.CharacterDTO
import com.darthside.marvelissimo.models.series.SeriesDTO
import com.darthside.marvelissimo.models.character.CharacterDataWrapper
import com.darthside.marvelissimo.models.series.SeriesDataWrapper
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

    fun getSeriesCall(callback: (List<SeriesDTO>) -> Unit, seriesName : String) {

        url = "https://gateway.marvel.com/v1/public/series?titleStartsWith=$seriesName&ts=$ts&apikey=$apiKey&hash=$hash"
        Log.d(httpTag, "Attempting request")
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val response = response.body()?.string()
                println("SeriesDataWrapper body: $response")

                val gson = GsonBuilder().create()
                val jsonData = gson.fromJson(response, SeriesDataWrapper::class.java)
                val seriesList = mutableListOf<SeriesDTO>()

                if (jsonData.data.results.isEmpty()) {
                    Log.d(httpTag, "No series found")
                }

                for (s in jsonData.data.results) {
                    seriesList.add(s)
                }
                callback(seriesList)


            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

    fun getAllCharactersCall(callback: (List<CharacterDTO>) -> Unit) {

        url = "https://gateway.marvel.com/v1/public/characters?&ts=$ts&apikey=$apiKey&hash=$hash"
        Log.d(httpTag, "Attempting request")
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val response = response.body()?.string()
                println("CharacterDataWrapper body: $response")

                val gson = GsonBuilder().create()
                val jsonData = gson.fromJson(response, CharacterDataWrapper::class.java)

                if (jsonData.data.results.isNotEmpty()) {
                    val characterList = jsonData.data.results
                    callback(characterList)
                } else {
                    Log.d(httpTag, "No characters found when calling getAllCharacters")
                }
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

    fun getAllSeriesCall(callback: (List<SeriesDTO>) -> Unit) {

        url = "https://gateway.marvel.com/v1/public/series?&ts=$ts&apikey=$apiKey&hash=$hash"
        Log.d(httpTag, "Attempting request")
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val response = response.body()?.string()
                println("SeriesDataWrapper body: $response")

                val gson = GsonBuilder().create()
                val jsonData = gson.fromJson(response, SeriesDataWrapper::class.java)

                if (jsonData.data.results.isNotEmpty()) {
                    val seriesList = jsonData.data.results
                    callback(seriesList)
                } else {
                    Log.d(httpTag, "No series found when calling getAllSeries")
                }
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

    fun getSeriesById(callback: (List<SeriesDTO>) -> Unit, seriesId : Int) {

        url = "https://gateway.marvel.com/v1/public/series/$seriesId?ts=$ts&apikey=$apiKey&hash=$hash"
        Log.d(httpTag, "Attempting request")
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val response = response.body()?.string()
                println("SeriesDataWrapper body: $response")

                val gson = GsonBuilder().create()
                val jsonData = gson.fromJson(response, SeriesDataWrapper::class.java)
                val seriesList = mutableListOf<SeriesDTO>()

                if (jsonData.data.results.isEmpty()) {
                    Log.d(httpTag, "No series found")
                }

                for (s in jsonData.data.results) {
                    seriesList.add(s)
                }
                callback(seriesList)


            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

    fun getCharacterById(callback: (List<CharacterDTO>) -> Unit, characterId: Int){

        url = "https://gateway.marvel.com/v1/public/characters/$characterId?&ts=$ts&apikey=$apiKey&hash=$hash"

        Log.d(httpTag, "Attempting request")
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val response = response.body()?.string()
                println("CharacterDataWrapper body: $response")

                val gson = GsonBuilder().create()
                val jsonData = gson.fromJson(response, CharacterDataWrapper::class.java)

                if (jsonData.data.results.isNotEmpty()) {
                    val characterList = jsonData.data.results
                    callback(characterList)

                }
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

    fun searchSeries(callback: (List<SeriesDTO>) -> Unit, seriesTitle : String) {

        url = "https://gateway.marvel.com/v1/public/series?titleStartsWith=$seriesTitle&ts=$ts&apikey=$apiKey&hash=$hash"
        Log.d(httpTag, "Attempting request")
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val response = response.body()?.string()
                println("SeriesDataWrapper body: $response")

                val gson = GsonBuilder().create()
                val jsonData = gson.fromJson(response, SeriesDataWrapper::class.java)

                if (jsonData.data.results.isNotEmpty()) {
                    val seriesList = jsonData.data.results
                    callback(seriesList)

                } else {
                    Log.d(httpTag, "No series details found when calling searchSeries")

                }
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

    fun searchCharacter(callback: (List<CharacterDTO>) -> Unit, characterName : String) {

        url = "https://gateway.marvel.com/v1/public/characters?nameStartsWith=$characterName&ts=$ts&apikey=$apiKey&hash=$hash"
        Log.d(httpTag, "Attempting request")
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val response = response.body()?.string()
                println("CharacterDataWrapper body: $response")

                val gson = GsonBuilder().create()
                val jsonData = gson.fromJson(response, CharacterDataWrapper::class.java)

                if (jsonData.data.results.isNotEmpty()) {
                    val characterList = jsonData.data.results
                    callback(characterList)

                } else {
                    Log.d(httpTag, "No character details found when calling searchCharacter")

                }
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }
}