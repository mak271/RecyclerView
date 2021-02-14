package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapterTextWithCartoonsTogether(private val name: List<String>, private val img: IntArray) : RecyclerView.Adapter<MyAdapterTextWithCartoonsTogether.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val listId: Int = R.layout.list_item_together
        val itemView = LayoutInflater.from(parent.context).inflate(listId,parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txt.text = name[position]
        Picasso.get().load(img[position]).into(holder.image)
    }

    override fun getItemCount(): Int {
        return name.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt: TextView
        val image: ImageView

        init {
            txt = itemView.findViewById(R.id.tv_name)
            image = itemView.findViewById(R.id.roundedImageView)
        }

    }
}