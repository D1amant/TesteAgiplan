package com.example.luisfernandorodrigues.testeagibank.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

class Item @Inject constructor() {

    @field:SerializedName("name")
    val name: String? = null

    @field:SerializedName("resourceURI")
    val resourceURI: String? = null
}