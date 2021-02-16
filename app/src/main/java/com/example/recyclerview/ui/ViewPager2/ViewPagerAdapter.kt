package com.example.recyclerview.ui.ViewPager2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.squareup.picasso.Picasso

class ViewPagerAdapter(private val title: List<String>, private val image: List<Int>): RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val numberList: Int = R.layout.list_item_viewpager
        val itemView = LayoutInflater.from(parent.context).inflate(numberList, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = title[position]
        Picasso.get().load(image[position]).into(holder.image)
    }

    override fun getItemCount(): Int {
        return image.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView
        val image: ImageView

        init {
            title = itemView.findViewById(R.id.tv_title)
            image = itemView.findViewById(R.id.tv_image)
        }

    }
}