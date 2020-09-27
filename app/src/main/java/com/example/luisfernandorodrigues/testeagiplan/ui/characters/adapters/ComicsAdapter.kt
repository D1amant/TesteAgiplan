package com.example.luisfernandorodrigues.testeagiplan.ui.characters.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.luisfernandorodrigues.testeagiplan.model.Item
import com.example.luisfernandorodrigues.testeagiplan.R
import kotlinx.android.synthetic.main.adapter_commics.view.*

class ComicsAdapter(private val context: Context, private val comicsList: List<Item>) :
    RecyclerView.Adapter<ComicsAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
            ListViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_commics, parent, false))

    override fun getItemCount(): Int =
            if (comicsList.isNotEmpty()) comicsList.size else 0

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val comic: Item? = comicsList[position]
        holder.tvName.text = comic?.name
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName = view.tv_comics_name
    }
}
