package com.example.recyclerview.ui.OnlyText

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.MyAdapterOnlyText
import com.example.recyclerview.R

class OnlyTextFragment : Fragment() {

    private lateinit var onlyTextViewModel: OnlyTextViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        onlyTextViewModel = ViewModelProvider(this).get(OnlyTextViewModel::class.java)
        val root = inflater.inflate(R.layout.main_content, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.rcView) // находим  RecyclerView

        onlyTextViewModel.text.observe(viewLifecycleOwner, Observer {
            recyclerView.layoutManager = LinearLayoutManager(context)  // список будет по умолчанию вертикальным
            recyclerView.setHasFixedSize(true)                         // список будет определённого размера
            recyclerView.adapter = MyAdapterOnlyText(fill())           // передаём метод fill() адаптеру
        })
        return root
    }

    private fun fill() : List<String> {                                // метод для получения списка котов из ресурсов
        return this.resources.getStringArray(R.array.text).toList()
    }
}