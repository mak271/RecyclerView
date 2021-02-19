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

class MyAdapterDatabase(private var userCursor: Cursor, private val image: List<Int>): RecyclerView.Adapter<MyAdapterDatabase.MyViewHolder>() {

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val listID: Int = R.layout.list_item_database
        val itemView = LayoutInflater.from(parent.context).inflate(listID, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Picasso.get().load(image[position]).into(holder.imageView)
        userCursor.moveToPosition(position)
        holder.bind(userCursor)

        //context?.let { Glide.with(it).load(image[position]).into(holder.imageView)}
    }

    fun updateAdapter(sql: SQLiteDatabase, dbHelper: DatabaseHelper)
    {

        dbHelper.onUpgrade(sql,1,1)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return userCursor.count
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val yearView: TextView = itemView.findViewById(R.id.tv_number_database)
        val nameView: TextView = itemView.findViewById(R.id.tv_name_database)
        val imageView: ImageView = itemView.findViewById(R.id.roundedImageView_database)

        fun bind(cursor: Cursor) {
            val name = cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME)
            val year = cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NUMBER)
            nameView.text = cursor.getString(name)
            yearView.text = cursor.getString(year)
        }

    }

}