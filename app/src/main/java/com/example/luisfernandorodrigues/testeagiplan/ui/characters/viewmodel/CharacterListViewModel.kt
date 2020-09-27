package com.example.luisfernandorodrigues.testeagiplan.ui.characters.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import com.example.luisfernandorodrigues.testeagiplan.helper.HashGenerate
import com.example.luisfernandorodrigues.testeagiplan.model.CharacterResponse
import com.example.luisfernandorodrigues.testeagiplan.R
import com.example.luisfernandorodrigues.testeagiplan.repository.CharacterRepository
import com.example.luisfernandorodrigues.testeagiplan.repository.ResponseInterface

class CharacterListViewModel : ViewModel(), ResponseInterface<List<CharacterResponse>> {

    var characterListObserver = MutableLiveData<List<CharacterResponse>>()
    var errorObserver = MutableLiveData<String>()

    fun getCharacters(context: Context) {
        val hashGenerate: HashGenerate? = HashGenerate()
        val ts: String = hashGenerate!!.ts
        val hash: String? = hashGenerate.getHash(ts, context.getString(R.string.privatekey), context.getString(R.string.apikey))
        val repository = CharacterRepository.getInstaceList(context, this)
        repository.getCharacters(ts, context.getString(R.string.apikey), hash!!)
    }

    override fun success(response: List<CharacterResponse>) {
        characterListObserver.postValue(response)
    }

    override fun error(message: String) {
        errorObserver.postValue(message)
    }
}
