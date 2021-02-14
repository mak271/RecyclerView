package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapterOnlyText(private val value: List<String>) : RecyclerView.Adapter<MyAdapterOnlyText.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutId: Int = R.layout.list_item_text
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_item.text = value[position]
    }

    override fun getItemCount(): Int {
        return value.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tv_item: TextView

        init {
            tv_item = itemView.findViewById(R.id.tv_item)
        }

    }

}