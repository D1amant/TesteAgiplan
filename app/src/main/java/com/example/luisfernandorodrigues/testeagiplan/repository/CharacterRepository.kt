package com.example.luisfernandorodrigues.testeagiplan.repository

import android.content.Context
import com.example.luisfernandorodrigues.testeagiplan.model.CharacterResponse
import com.example.luisfernandorodrigues.testeagiplan.services.CharacterService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import com.example.luisfernandorodrigues.testeagiplan.model.Response
import com.example.luisfernandorodrigues.testeagiplan.R

object CharacterRepository : BaseRepository<CharacterService, JsonObject>() {

    private const val INTEVAL: Int = 20
    var responseList: ResponseInterface<List<CharacterResponse>>? = null
    var mResponse: ResponseInterface<CharacterResponse>? = null
    var offset: Int? = 0

    fun getInstaceList(context: Context, responseList: ResponseInterface<List<CharacterResponse>>): CharacterRepository {
        this.responseList = responseList
        init(context, CharacterService::class.java)

        return this
    }

    fun getCharacters(ts: String, apikey: String, hash: String) {
        offset = offset!! + INTEVAL
        getService().getCharacters(ts, apikey, hash, offset!!).enqueue(object : Callback<Response<List<CharacterResponse>>> {
            override fun onResponse(call: Call<Response<List<CharacterResponse>>>?, response: retrofit2.Response<Response<List<CharacterResponse>>>?) {
                if (response!!.body() == null || response.body()!!.data.total.isNullOrBlank() || offset == response.body()!!.data.total!!.toInt()) {
                    responseList!!.error(appContext!!.getString(R.string.message_characters))
                } else {
                    responseList!!.success(response.body()!!.data.results)
                }
            }

            override fun onFailure(call: Call<Response<List<CharacterResponse>>>?, t: Throwable) {
                responseList!!.error(t.message!!)
            }
        })
    }

    fun getCharacter(charactersId: Int, ts: String, apikey: String, hash: String) {
        getService().getCharacter(charactersId, ts, apikey, hash).enqueue(object : Callback<Response<List<CharacterResponse>>> {
            override fun onResponse(call: Call<Response<List<CharacterResponse>>>?, response: retrofit2.Response<Response<List<CharacterResponse>>>?) {
                if (response!!.body() == null || response.body()!!.data.total.isNullOrBlank() || offset == response.body()!!.data.total!!.toInt()) {
                    responseList!!.error(appContext!!.getString(R.string.message_character_error))
                } else {
                    responseList!!.success(response.body()!!.data.results)
                }
            }

            override fun onFailure(call: Call<Response<List<CharacterResponse>>>?, t: Throwable) {
                mResponse!!.error(t.message!!)
            }
        })
    }
}