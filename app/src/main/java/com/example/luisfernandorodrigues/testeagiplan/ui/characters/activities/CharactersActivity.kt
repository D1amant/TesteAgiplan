package com.example.luisfernandorodrigues.testeagiplan.ui.characters.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.luisfernandorodrigues.testeagiplan.R
import com.example.luisfernandorodrigues.testeagiplan.model.CharacterResponse
import com.example.luisfernandorodrigues.testeagiplan.ui.characters.adapters.CharactersAdapter
import com.example.luisfernandorodrigues.testeagiplan.ui.characters.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class CharactersActivity : AppCompatActivity(), UpdateListInterface {

    lateinit var model: CharacterListViewModel
    lateinit var recyclerView: RecyclerView
    private lateinit var characterAdapter: CharactersAdapter
    private lateinit var characters: ArrayList<CharacterResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        observers()

        model.getCharacters(applicationContext)
        progressBar.visibility = View.VISIBLE
    }

    private fun init() {
        model = ViewModelProvider(this).get(CharacterListViewModel::class.java)
        recyclerView = recycler_view
        recyclerView.layoutManager = LinearLayoutManager(this)
        characters = ArrayList()
        characterAdapter = CharactersAdapter(applicationContext, characters, this)
        recyclerView.adapter = characterAdapter
    }

    private fun observers() {
        model.characterListObserver.observe(this, { characters -> updateList(characters!!) })
    }

    private fun updateList(characters: List<CharacterResponse>) {
        this.characters.addAll(characters)
        characterAdapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
    }

    override fun onUpdate(canUpdate: Boolean) {
        model.getCharacters(applicationContext)
    }
}
