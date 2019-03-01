package com.darthside.marvelissimo

import com.darthside.marvelissimo.entities.CharacterResult
import retrofit2.Call
import retrofit2.http.GET

interface GetCharactersService {

    @GET("characters?name=deadpool&apikey=174943a97b8c08a00a80d1ed425d9ed1")
    fun getAllCharacters() : Call<CharacterResult>
}