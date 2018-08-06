package com.example.luisfernandorodrigues.testeagiplan.Ui.Characters.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.luisfernandorodrigues.testeagiplan.Model.CharacterResponse
import com.example.luisfernandorodrigues.testeagiplan.R
import com.example.luisfernandorodrigues.testeagiplan.Ui.Characters.Adapters.CharactersAdapter
import com.example.luisfernandorodrigues.testeagiplan.Ui.Characters.ViewModel.CharacterListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class CharactersActivity : AppCompatActivity(),UpdateListInterface {

    var model : CharacterListViewModel? = null
    lateinit var recyclerView : RecyclerView
    lateinit var charracterAdapter : CharactersAdapter
    lateinit var charactersLsit: ArrayList<CharacterResponse>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        observers()

        model!!.getChatacters(applicationContext)
        progressBar.visibility = View.VISIBLE
    }

    fun init (){
        model = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)
        recyclerView = recycler_view
        recyclerView.layoutManager = LinearLayoutManager(this)
        charactersLsit = ArrayList()
        charracterAdapter = CharactersAdapter(applicationContext , charactersLsit , this)
        recyclerView.adapter = charracterAdapter

    }
    fun observers(){
        model!!.characterListObserver.observe(this , Observer { characters -> updateList(characters!!) })
    }

    fun updateList(characters : List<CharacterResponse>){
         charactersLsit.addAll(characters)
         charracterAdapter.notifyDataSetChanged()
         progressBar.visibility = View.GONE
    }

    override fun onUpdate(exce: Boolean) {
        model!!.getChatacters(applicationContext)
        //progressBar.visibility = View.VISIBLE
    }

}
