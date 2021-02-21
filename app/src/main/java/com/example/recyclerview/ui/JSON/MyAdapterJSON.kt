package com.example.recyclerview.ui.JSON

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.squareup.picasso.Picasso

class MyAdapterJSON(private val itemList: List<RadioModel>): RecyclerView.Adapter<MyAdapterJSON.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutID: Int = R.layout.list_item_json
        val itemView = LayoutInflater.from(parent.context).inflate(layoutID, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position]

        holder.apply {

            nameView.text = item.name
            streamView.text = item.stream
            Picasso.get().load(item.img).into(imageView)

            itemView.setOnClickListener {
                val pos = adapterPosition
                itemList[pos].isSelected = !itemList[pos].isSelected
                notifyItemChanged(position)
            }

            if (item.isSelected)
                itemView.setBackgroundResource(R.color.grey)
            else
                itemView.setBackgroundResource(android.R.color.transparent)

        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun getSelectedList(): List<RadioModel> {
        return itemList.filter { it.isSelected }
    }

    fun clearSelection() {
        itemList.forEachIndexed { index, model ->
            if (model.isSelected) {
                model.isSelected = false
                notifyItemChanged(index)
            }
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.tv_name_radio)
        val streamView: TextView = itemView.findViewById(R.id.tv_stream_radio)
        val imageView: ImageView = itemView.findViewById(R.id.roundedImageView_radio)
    }

}