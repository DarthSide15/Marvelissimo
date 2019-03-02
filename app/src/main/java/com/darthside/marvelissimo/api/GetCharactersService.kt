package com.darthside.marvelissimo.api

import com.darthside.marvelissimo.models.CharacterResult
import retrofit2.Call
import retrofit2.http.GET

interface GetCharactersService {

    @GET("characters?name=deadpool&ts=1&apikey=174943a97b8c08a00a80d1ed425d9ed1&hash=8b36d2a14cd3a4cec60c30e9f70b8ab3")
    fun getAllCharacters() : Call<CharacterResult>
}