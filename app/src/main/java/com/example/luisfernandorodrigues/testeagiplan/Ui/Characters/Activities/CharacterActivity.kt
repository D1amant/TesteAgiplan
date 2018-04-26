package com.example.luisfernandorodrigues.testeagiplan.Ui.Characters.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.example.luisfernandorodrigues.testeagiplan.Model.CharacterResponse
import com.example.luisfernandorodrigues.testeagiplan.Model.Item
import com.example.luisfernandorodrigues.testeagiplan.R
import com.example.luisfernandorodrigues.testeagiplan.Ui.Characters.Adapters.ComicsAdapter
import com.example.luisfernandorodrigues.testeagiplan.Ui.Characters.ViewModel.CharacterViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character.*


class CharacterActivity : AppCompatActivity() {

    var model : CharacterViewModel? = null
    var character : CharacterResponse? = null
    var characterId : Int? = null
    var ites : ArrayList<Item>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        init()
        observer()
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        //actionBar.setDisplayHomeAsUpEnabled(true)
        characterId = intent.getIntExtra("characterId" , 0)
        if(characterId != 0){
            model!!.getChatacters(applicationContext, characterId!!)
        }
        cardHeader.visibility = View.GONE
        tvMessage.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    fun init(){
        recycler_view.layoutManager = LinearLayoutManager(this)
        model = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        recycler_view.adapter  = ComicsAdapter(applicationContext, ites!!)

    }

    fun observer(){
        model!!.characterListObserver.observe(this , Observer { character -> updateView(character!!) })
    }

    fun updateView(character : CharacterResponse){
        Picasso.get()
                .load(character.thumbnail!!.path+"."+character!!.thumbnail!!.extension)
                .into(image)
        tvNameValue.setText(character.name)
        ites!!.addAll(character.comics!!.items!!)
        recycler_view.adapter.notifyDataSetChanged()
        cardHeader.visibility = View.VISIBLE
        progressBar.visibility = View.GONE

        if(character.comics!!.items!!.size >= 0){
            tvMessage.visibility = View.GONE
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
