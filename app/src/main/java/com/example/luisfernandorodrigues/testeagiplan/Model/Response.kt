package com.example.luisfernandorodrigues.testeagiplan.Model

data class Response<T>(
	val copyright: String? = null,
	val code: Int? = null,
	val attributionHTML: String? = null,
	val attributionText: String? = null,
	val etag: String? = null,
	val status: String? = null,
	val data : Data<T>
)
