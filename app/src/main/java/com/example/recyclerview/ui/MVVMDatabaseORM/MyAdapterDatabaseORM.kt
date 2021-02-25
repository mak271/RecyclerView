package com.example.recyclerview.ui.MVVMDatabaseORM

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.squareup.picasso.Picasso

class MyAdapterDatabaseORM(val image: List<Int>): RecyclerView.Adapter<MyAdapterDatabaseORM.MyViewGolder>() {

    private var listItem = mutableListOf<DatabaseORMModel>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewGolder {
        val layoutID: Int = R.layout.list_item_databaseorm
        val item = LayoutInflater.from(parent.context).inflate(layoutID, parent, false)
        return MyViewGolder(item)
    }

    override fun onBindViewHolder(holder: MyViewGolder, position: Int) {

        val item = listItem[position]

        holder.apply {
            tvNumber.text = item.number.toString()
            tvName.text = item.name
            Picasso.get().load(image[position]).into(tvImage)
        }

    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    fun initList(list: List<DatabaseORMModel>) {
        listItem.clear()
        listItem.addAll(list)
        notifyDataSetChanged()
    }

    class MyViewGolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvNumber: TextView = itemView.findViewById(R.id.tv_number_databaseORM)
        val tvName: TextView = itemView.findViewById(R.id.tv_name_databaseORM)
        val tvImage: ImageView = itemView.findViewById(R.id.roundedImageView_databaseORM)
    }

}