package com.example.luisfernandorodrigues.testeagiplan.ui.characters.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.luisfernandorodrigues.testeagiplan.R
import com.example.luisfernandorodrigues.testeagiplan.model.CharacterResponse
import com.example.luisfernandorodrigues.testeagiplan.model.Item
import com.example.luisfernandorodrigues.testeagiplan.ui.characters.adapters.ComicsAdapter
import com.example.luisfernandorodrigues.testeagiplan.ui.characters.viewmodel.CharacterViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character.*

class CharacterActivity : AppCompatActivity() {

    lateinit var model: CharacterViewModel
    var characterId: Int? = null
    var ites: ArrayList<Item>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        init()
        observer()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        characterId = intent.getIntExtra(CHARACTERID, 0)
        if (characterId != 0) {
            model.getChatacters(applicationContext, characterId!!)
        }
        cardHeader.visibility = View.GONE
        tvMessage.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun init() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        model = ViewModelProvider(this).get(CharacterViewModel::class.java)
        recycler_view.adapter = ComicsAdapter(applicationContext, ites!!)
    }

    private fun observer() {
        model.characterListObserver.observe(this, { character -> updateView(character!!) })
        model.errorObserver.observe(this, { message -> error(message!!) })
    }

    private fun updateView(character: CharacterResponse) {
        Picasso.get()
                .load("${character.thumbnail?.path}.${character.thumbnail?.extension}"
                        .replace(":", "s:"))
                .into(image)
        tvNameValue.text = character.name
        ites?.addAll(character.comics!!.items!!)
        recycler_view.adapter?.notifyDataSetChanged()
        cardHeader.visibility = View.VISIBLE
        progressBar.visibility = View.GONE

        tvMessage.visibility = if (character.comics?.items!!.isNotEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun error(message: String) {
        tvMessage.visibility = View.VISIBLE
        tvMessage.text = message
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        var CHARACTERID = "characterId"
    }
}
