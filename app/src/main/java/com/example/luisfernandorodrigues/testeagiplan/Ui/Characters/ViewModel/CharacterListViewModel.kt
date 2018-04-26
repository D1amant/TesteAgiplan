package com.example.luisfernandorodrigues.testeagiplan.Ui.Characters.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.luisfernandorodrigues.testeagiplan.Helper.HashGenerate
import com.example.luisfernandorodrigues.testeagiplan.Model.CharacterResponse
import com.example.luisfernandorodrigues.testeagiplan.R
import com.example.luisfernandorodrigues.testeagiplan.Repository.CharacterRepository
import com.example.luisfernandorodrigues.testeagiplan.Repository.ResponseInterface

class CharacterListViewModel: ViewModel(), ResponseInterface<List<CharacterResponse>> {

    var characterListObserver = MutableLiveData<List<CharacterResponse>>()
    var errorObserver = MutableLiveData<String>()


    fun getChatacters(context : Context){
        var hashGenerate : HashGenerate? = HashGenerate()
        var ts : String? = hashGenerate!!.ts
        if (!ts.isNullOrBlank()) {
            var hash : String? =  hashGenerate.getHash(ts!! , context.getString(R.string.privatekey), context.getString(R.string.apikey))
            var repository  = CharacterRepository.getInstaceList(context, this)
            repository!!.getCharacters(ts ,  context.getString(R.string.apikey), hash!!)
        }
    }

    override fun success(response: List<CharacterResponse>) {
        characterListObserver.postValue(response)
    }

    override fun error(message: String) {
        errorObserver.postValue(message)
    }

}