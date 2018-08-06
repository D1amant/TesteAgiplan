package com.example.luisfernandorodrigues.testeagiplan.Ui.Characters.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.luisfernandorodrigues.testeagiplan.Model.Item
import com.example.luisfernandorodrigues.testeagiplan.R
import kotlinx.android.synthetic.main.adapter_commics.view.*

class ComicsAdapter(val context : Context, val comicsList: List<Item>) : RecyclerView.Adapter<ComicsAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_commics, parent, false)
        // Cria o ViewHolder
        val holder = ListViewHolder(view)

        return holder
    }

    override fun getItemCount(): Int {
        return if(comicsList.isNotEmpty()) comicsList.size else 0 //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var comic  :Item? = comicsList.get(position)

        holder.tvName.setText(comic!!.name)

    }


    class ListViewHolder(view: View)  : RecyclerView.ViewHolder(view) {
        var tvName = view.tv_comics_name
    }
}