package com.example.luisfernandorodrigues.testeagiplan.services

import com.example.luisfernandorodrigues.testeagiplan.model.CharacterResponse
import com.example.luisfernandorodrigues.testeagiplan.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET("characters")
    fun getCharacters(
        @Query("ts")
        ts: String,
        @Query("apikey")
        apikey: String,
        @Query("hash")
        hash: String,
        @Query("offset") offset: Int
    ): Call<Response<List<CharacterResponse>>>

    @GET("characters/{characterId}")
    fun getCharacter(
        @Path("characterId")
        characterId: Int,
        @Query("ts")
        ts: String,
        @Query("apikey")
        apikey: String,
        @Query("hash")
        hash: String
    ): Call<Response<List<CharacterResponse>>>
}