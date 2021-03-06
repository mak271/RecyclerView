package com.example.recyclerview.ui.OnlyCartoon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.squareup.picasso.Picasso

class MyAdapterOnlyCartoons(private val image: List<Int>) : RecyclerView.Adapter<MyAdapterOnlyCartoons.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val listID: Int = R.layout.list_item_image
        val itemView = LayoutInflater.from(parent.context).inflate(listID,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(image[position]).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return image.size
    }


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.tv_image)
        }
    }

}