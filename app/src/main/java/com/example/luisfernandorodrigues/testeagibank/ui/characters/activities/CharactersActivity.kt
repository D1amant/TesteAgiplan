package com.example.luisfernandorodrigues.testeagibank.ui.characters.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.luisfernandorodrigues.testeagibank.R
import com.example.luisfernandorodrigues.testeagibank.model.CharacterResponse
import com.example.luisfernandorodrigues.testeagibank.ui.characters.adapters.CharactersAdapter
import com.example.luisfernandorodrigues.testeagibank.ui.characters.viewmodel.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
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
