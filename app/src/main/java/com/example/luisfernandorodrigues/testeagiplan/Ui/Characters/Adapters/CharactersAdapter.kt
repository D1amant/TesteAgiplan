package com.example.luisfernandorodrigues.testeagiplan.Ui.Characters.Adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.luisfernandorodrigues.testeagiplan.Model.CharacterResponse
import com.example.luisfernandorodrigues.testeagiplan.R
import com.example.luisfernandorodrigues.testeagiplan.Ui.Characters.Activities.CharacterActivity
import com.example.luisfernandorodrigues.testeagiplan.Ui.Characters.Activities.UpdateListInterface
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_character.view.*

class CharactersAdapter(val context : Context, val characterList: List<CharacterResponse> , val response : UpdateListInterface) : RecyclerView.Adapter<CharactersAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_character, parent, false)
        // Cria o ViewHolder
        val holder = ListViewHolder(view)

        return holder
    }

    override fun getItemCount(): Int {
        return if(characterList.isNotEmpty()) characterList!!.size else 0 //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var characterResponse  :CharacterResponse? = characterList.get(position)

        holder.tvName.setText(characterResponse!!.name)
        Picasso.get()
                .load(characterResponse!!.thumbnail!!.path+"."+characterResponse!!.thumbnail!!.extension)
                .into(holder.image)

        if(position == characterList.size -1){
            holder.progressBar.visibility = View.VISIBLE
            response.onUpdate(true)
        }else{
            holder.progressBar.visibility = View.GONE
        }

        holder.cardView.setOnClickListener {
          var  intent = Intent(context , CharacterActivity::class.java)
            intent.putExtra("characterId" , characterResponse.id)
            context.startActivity(intent)
        }

    }


    class ListViewHolder(view: View)  : RecyclerView.ViewHolder(view) {
        var tvName = view.tv_name_value
        var image = view.image
        var progressBar = view.progressBar
        var cardView = view.cardHeader

    }
}