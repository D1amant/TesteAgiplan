package com.example.luisfernandorodrigues.testeagibank.repository

interface ResponseInterface<T> {
    fun success(response: T)

    fun error(message: String)
}
