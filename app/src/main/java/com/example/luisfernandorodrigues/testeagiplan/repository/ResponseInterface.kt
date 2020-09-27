package com.example.luisfernandorodrigues.testeagiplan.repository

interface ResponseInterface<T> {
    fun success(response: T)

    fun error(message: String)
}
