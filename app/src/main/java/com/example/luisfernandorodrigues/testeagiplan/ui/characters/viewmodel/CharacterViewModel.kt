package com.example.luisfernandorodrigues.testeagiplan.ui.characters.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import com.example.luisfernandorodrigues.testeagiplan.helper.HashGenerate
import com.example.luisfernandorodrigues.testeagiplan.model.CharacterResponse
import com.example.luisfernandorodrigues.testeagiplan.R
import com.example.luisfernandorodrigues.testeagiplan.repository.CharacterRepository
import com.example.luisfernandorodrigues.testeagiplan.repository.ResponseInterface

class CharacterViewModel : ViewModel(), ResponseInterface<List<CharacterResponse>> {

    var characterListObserver = MutableLiveData<CharacterResponse>()
    var errorObserver = MutableLiveData<String>()

    fun getChatacters(context: Context, charactersId: Int) {
        val hashGenerate: HashGenerate? = HashGenerate()
        val ts: String? = hashGenerate!!.ts
        if (!ts.isNullOrBlank()) {
            val hash: String = hashGenerate.getHash(ts, context.getString(R.string.privatekey), context.getString(R.string.apikey))
            val repository = CharacterRepository.getInstaceList(context, this)
            repository.getCharacter(charactersId, ts, context.getString(R.string.apikey), hash)
        }
    }

    override fun success(response: List<CharacterResponse>) {
        characterListObserver.postValue(response.get(0))
    }

    override fun error(message: String) {
        errorObserver.postValue(message)
    }
}
