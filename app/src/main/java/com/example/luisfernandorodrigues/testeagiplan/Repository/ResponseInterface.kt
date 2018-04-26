package com.example.luisfernandorodrigues.testeagiplan.Repository

interface ResponseInterface<T> {


     fun success(response : T)

     fun error(message : String)
}