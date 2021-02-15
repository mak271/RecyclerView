package com.example.recyclerview.ui.OnlyText

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

class MyAdapterOnlyText(private val value: List<String>) : RecyclerView.Adapter<MyAdapterOnlyText.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {      // этот метод вызывается при создании нового ViewHolder
        val layoutId: Int = R.layout.list_item_text
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent,false) // создание элемента списка, созданного нами ранее в файле list_item_text
        return MyViewHolder(itemView)                                                      // эллемент списка оборачивается во ViewHolder и возвращается в RecyclerView
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { // этот метод вызывается, когда RecyclerView хочет уже созданые ViewHolder использовать заново
        holder.tv_item.text = value[position]                            // связываем используемые текстовые метки с данными и обновляем значение TextView у ViewHolder
    }

    override fun getItemCount(): Int { // определяем длину массива, возвращает кол-во элементов в списке
        return value.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { // ViewHolder оборачивает в себя элемент списка и находит нужные компоненты из этого элемента списка

        var tv_item: TextView

        init {
            tv_item = itemView.findViewById(R.id.tv_item)
        }

    }

}