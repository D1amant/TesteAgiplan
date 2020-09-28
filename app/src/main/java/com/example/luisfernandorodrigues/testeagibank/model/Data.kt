package com.example.luisfernandorodrigues.testeagibank.model

class Data<T> (
    val offset: String? = null,
    val total: String? = null,
    val count: String? = null,
    val results: T
)
