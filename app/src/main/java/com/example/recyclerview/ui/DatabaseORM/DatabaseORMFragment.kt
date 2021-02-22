package com.example.recyclerview.ui.DatabaseORM

import android.annotation.SuppressLint
import android.content.res.TypedArray
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.recyclerview.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class DatabaseORMFragment: Fragment() {



    private var daoDatabaseORM: DAODatabaseORM? = null
    private var db: ORMDatabase? = null




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.database_orm_main, container, false)

        db = ORMDatabase.getOrmDatabase(requireContext())
        daoDatabaseORM = db?.daoDatabaseORM()

        root.apply {

            val number: EditText = findViewById(R.id.yearORM_editText)
            val name: EditText = findViewById(R.id.name_editTextORM)
            val search: EditText = findViewById(R.id.searchORM_editText)

            val recyclerView: RecyclerView = findViewById(R.id.recycler_ORM)
            recyclerView.layoutManager = LinearLayoutManager(context)
            val adapter = MyAdapterDatabaseORM(fill())
            recyclerView.adapter = adapter
            GlobalScope.launch(Dispatchers.IO) {
                reload(adapter)
            }

            findViewById<Button>(R.id.btn_saveORM).setOnClickListener {
                if (number.text == null || number.text.toString() == "" || name.text == null || name.text.toString() == "") {
                    Toast.makeText(requireContext(), "Заполните оба поля", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                GlobalScope.launch(Dispatchers.IO) {
                    val dates = DatabaseORMModel(Integer.parseInt(number.text.toString()), name.text.toString())
                    daoDatabaseORM?.insert(dates)
                    number.text.clear()
                    name.text.clear()
                    reload(adapter)
                }

            }

            findViewById<Button>(R.id.btn_deleteORM).setOnClickListener {
                if (number.text == null || number.text.toString() == "") {
                    Toast.makeText(requireContext(), "Введите номер записи", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                GlobalScope.launch(Dispatchers.IO) {
                    if (daoDatabaseORM?.selectByID(Integer.parseInt(number.text.toString())) != null) {
                        daoDatabaseORM?.deleteByID(Integer.parseInt(number.text.toString()))
                        number.text.clear()
                        reload(adapter)
                    }
                    else
                        Toast.makeText(requireContext(), "Такой записи не существует", Toast.LENGTH_SHORT).show()
                }
            }

            findViewById<Button>(R.id.btn_searchORM).setOnClickListener {
                GlobalScope.launch(Dispatchers.IO) {
                    val searchText = search.text.toString()
                    if (searchText == "")
                        reload(adapter)
                    else {
                        val searchList = daoDatabaseORM?.selectByName("%$searchText%")
                        if (searchList != null)
                            requireActivity().runOnUiThread {
                                adapter.initList(searchList)
                            }
                    }
                }
            }

        }

        return root
    }

    private fun fill() : List<Int> {
        return this.getImageId(R.array.cats_cartoons)
    }

    fun getImageId(imageArrayId:Int):List<Int>
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


    fun reload(adapter: MyAdapterDatabaseORM) {
        val value = daoDatabaseORM?.selectAll()
        if (value != null) {
            requireActivity().runOnUiThread {
                adapter.initList(value.toList())
            }
        }
    }


}





















