package com.example.luisfernandorodrigues.testeagibank.di

import androidx.lifecycle.MutableLiveData
import com.example.luisfernandorodrigues.testeagibank.model.CharacterResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ApplicationModule {

    @Provides
    fun characterObserver(): MutableLiveData<CharacterResponse> =
            MutableLiveData<CharacterResponse>()

    @Provides
    fun errorObserver(): MutableLiveData<String> = MutableLiveData<String>()

    @Provides
    fun characterListObserver(): MutableLiveData<List<CharacterResponse>> =
            MutableLiveData<List<CharacterResponse>>()
}