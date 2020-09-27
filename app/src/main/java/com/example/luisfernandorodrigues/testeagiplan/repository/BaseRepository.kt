package com.example.luisfernandorodrigues.testeagiplan.repository

import android.content.Context
import com.example.luisfernandorodrigues.testeagiplan.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseRepository<T, J> {
    var retrofit: Retrofit? = null
    var myClass: Class<T>? = null
    var appContext: Context? = null

    fun init(context: Context, myClass: Class<T>) {
        this.myClass = myClass
        retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        this.appContext = context
    }

    fun getService(): T {
        return retrofit!!.create(myClass!!)
    }
}