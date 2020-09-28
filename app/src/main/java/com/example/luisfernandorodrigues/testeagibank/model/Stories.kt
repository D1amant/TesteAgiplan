package com.example.luisfernandorodrigues.testeagibank.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

class Stories @Inject constructor() {

    @field:SerializedName("collectionURI")
    val collectionURI: String? = null

    @field:SerializedName("available")
    val available: Int? = null

    @field:SerializedName("returned")
    val returned: Int? = null

    @field:SerializedName("items")
    val items: List<Any?>? = null
}