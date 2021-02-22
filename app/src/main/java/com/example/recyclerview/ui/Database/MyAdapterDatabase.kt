package com.example.recyclerview.ui.Database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.R
import com.squareup.picasso.Picasso
import okhttp3.internal.closeQuietly
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

class MyAdapterDatabase(private val image: List<Int>): RecyclerView.Adapter<MyAdapterDatabase.MyViewHolder>() {

    private val itemList = mutableListOf<DatabaseModel>()

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val listID: Int = R.layout.list_item_database
        val itemView = LayoutInflater.from(parent.context).inflate(listID, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Picasso.get().load(image[position]).into(holder.imageView)
        val item = itemList[position]
        holder.yearView.text = item.number.toString()
        holder.nameView.text = item.description

    }

    fun updateAdapter(list: List<DatabaseModel>)
    {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val yearView: TextView = itemView.findViewById(R.id.tv_number_database)
        val nameView: TextView = itemView.findViewById(R.id.tv_name_database)
        val imageView: ImageView = itemView.findViewById(R.id.roundedImageView_database)

    }

}