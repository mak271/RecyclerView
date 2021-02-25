package com.example.recyclerview.ui.MVVMDatabaseORM

import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DatabaseORMFragment: Fragment() {

    private var myViewModel: MyViewModel? = null

    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.database_orm_main, container, false)

        root.apply {

            val number: EditText = findViewById(R.id.yearORM_editText)
            val name: EditText = findViewById(R.id.name_editTextORM)
            val search: EditText = findViewById(R.id.searchORM_editText)

            val recyclerView: RecyclerView = findViewById(R.id.recycler_ORM)
            recyclerView.layoutManager = LinearLayoutManager(context)
            val adapter = MyAdapterDatabaseORM(fill())
            recyclerView.adapter = adapter

            myViewModel = ViewModelProvider(this@DatabaseORMFragment).get(MyViewModel::class.java)
            myViewModel?.selectAll(context)?.observe(viewLifecycleOwner, Observer {
                adapter.initList(it)
            })

            findViewById<Button>(R.id.btn_saveORM).setOnClickListener {
                if (number.text == null || number.text.toString() == "" || name.text == null || name.text.toString() == "") {
                    Toast.makeText(requireContext(), "Заполните оба поля", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                else {
                    myViewModel?.insert(
                        context,
                        DatabaseORMModel(
                            Integer.parseInt(number.text.toString()),
                            name.text.toString()
                        )
                    )
                    number.text.clear()
                    name.text.clear()
                }


            }

            findViewById<Button>(R.id.btn_deleteORM).setOnClickListener {
                if (number.text == null || number.text.toString() == "") {
                    Toast.makeText(requireContext(), "Введите номер записи", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (myViewModel?.selectById(context, Integer.parseInt(number.text.toString())) != null) {

                    myViewModel?.deleteById(context, Integer.parseInt(number.text.toString()))
                    number.text.clear()
                }
                else
                    Toast.makeText(requireContext(), "Такой записи не существует", Toast.LENGTH_SHORT).show()

            }

            findViewById<Button>(R.id.btn_searchORM).setOnClickListener {
                    val searchText = search.text.toString()
                    myViewModel?.selectByName(context, "%$searchText%")?.observe(viewLifecycleOwner, Observer {
                        adapter.initList(it)
                    })
            }

        }
        return root
    }

    private fun fill() : List<Int> {
        return this.getImageId(R.array.cats_cartoons)
    }

    private fun getImageId(imageArrayId:Int):List<Int>
    {
        val tArray: TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val ids = IntArray(count)
        for (i in ids.indices)
        {
            ids[i] = tArray.getResourceId(i,0)
        }
        tArray.recycle()
        return ids.toList()
    }

}





















