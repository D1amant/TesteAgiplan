package com.example.luisfernandorodrigues.testeagiplan.ui.characters.adapters

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.luisfernandorodrigues.testeagiplan.model.CharacterResponse
import com.example.luisfernandorodrigues.testeagiplan.R
import com.example.luisfernandorodrigues.testeagiplan.ui.characters.activities.CharacterActivity
import com.example.luisfernandorodrigues.testeagiplan.ui.characters.activities.UpdateListInterface
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_character.view.*

class CharactersAdapter(
    private val context: Context,
    private val characterList: List<CharacterResponse>,
    private val response: UpdateListInterface
) : RecyclerView.Adapter<CharactersAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
            ListViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.adapter_character, parent, false))

    override fun getItemCount(): Int {
        return if (characterList.isNotEmpty()) characterList.size else 0 // To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val characterResponse: CharacterResponse? = characterList[position]

        holder.tvName.text = characterResponse!!.name
        Picasso.get()
                .load("${characterResponse.thumbnail!!.path}.${characterResponse.thumbnail.extension}"
                        .replace(":", "s:")
                )
                .error(ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground)!!)
                .into(holder.image)

        if (position == characterList.size - 1) {
            holder.progressBar.visibility = View.VISIBLE
            response.onUpdate(true)
        } else {
            holder.progressBar.visibility = View.GONE
        }

        holder.cardView.setOnClickListener {
            context.startActivity(
                    Intent(context, CharacterActivity::class.java).apply {
                        flags = FLAG_ACTIVITY_NEW_TASK
                        putExtra("characterId", characterResponse.id)
                    }
            )
        }
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.tv_name_value
        var image: ImageView = view.image
        var progressBar: ProgressBar = view.progressBar
        var cardView: CardView = view.cardHeader
    }
}