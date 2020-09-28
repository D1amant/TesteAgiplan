package com.example.luisfernandorodrigues.testeagibank.ui.characters.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import com.example.luisfernandorodrigues.testeagibank.helper.HashGenerate
import com.example.luisfernandorodrigues.testeagibank.model.CharacterResponse
import com.example.luisfernandorodrigues.testeagibank.R
import com.example.luisfernandorodrigues.testeagibank.repository.CharacterRepository
import com.example.luisfernandorodrigues.testeagibank.repository.ResponseInterface

class CharacterListViewModel @ViewModelInject constructor(
    private val hashGenerate: HashGenerate,
    val characterListObserver: MutableLiveData<List<CharacterResponse>>,
    private val errorObserver: MutableLiveData<String>,
    private val repository: CharacterRepository
) : ViewModel(), ResponseInterface<List<CharacterResponse>> {

    fun getCharacters(context: Context) {
        val ts: String = hashGenerate.ts
        val hash: String? = hashGenerate.getHash(ts, context.getString(R.string.privatekey), context.getString(R.string.apikey))
        repository.run {
            setResponse(this@CharacterListViewModel)
            getCharacters(ts, context.getString(R.string.apikey), hash!!)
        }
    }

    override fun success(response: List<CharacterResponse>) {
        characterListObserver.postValue(response)
    }

    override fun error(message: String) {
        errorObserver.postValue(message)
    }
}
