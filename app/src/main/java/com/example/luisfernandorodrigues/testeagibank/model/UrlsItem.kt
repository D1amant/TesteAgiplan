package com.example.luisfernandorodrigues.testeagibank.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

class UrlsItem @Inject constructor() {

    @field:SerializedName("type")
    val type: String? = null

    @field:SerializedName("url")
    val url: String? = null
}