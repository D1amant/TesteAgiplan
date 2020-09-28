package com.example.luisfernandorodrigues.testeagibank.repository

import android.content.Context
import com.example.luisfernandorodrigues.testeagibank.model.CharacterResponse
import com.example.luisfernandorodrigues.testeagibank.services.CharacterService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import com.example.luisfernandorodrigues.testeagibank.model.Response
import com.example.luisfernandorodrigues.testeagibank.R
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class CharacterRepository @Inject constructor(@ActivityContext private val context: Context) : BaseRepository<CharacterService, JsonObject>() {

    var responseList: ResponseInterface<List<CharacterResponse>>? = null
    var mResponse: ResponseInterface<CharacterResponse>? = null
    var offset: Int? = 0

    fun setResponse(responseList: ResponseInterface<List<CharacterResponse>>) {
        this.responseList = responseList
        init(context, CharacterService::class.java)
    }

    fun getCharacters(ts: String, apikey: String, hash: String) {
        offset = offset!! + INTERVAL
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

    fun getCharacter(charactersId: Int, ts: String, apiKey: String, hash: String) {
        getService().getCharacter(charactersId, ts, apiKey, hash).enqueue(object : Callback<Response<List<CharacterResponse>>> {
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

    companion object {
        private const val INTERVAL: Int = 20
    }
}