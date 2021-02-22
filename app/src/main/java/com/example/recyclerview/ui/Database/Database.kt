package com.example.recyclerview.ui.Database

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.MainActivity
import com.example.recyclerview.R
import java.lang.Long.getLong

class Database: Fragment() {

    var recyclerView: RecyclerView? = null
    var btn_search: Button? = null

    var databaseHelper: DatabaseHelper? = null
    var database: SQLiteDatabase? = null
    var databaseAdapter: MyAdapterDatabase? = null

    var userCursor: Cursor? = null

    var name: EditText? = null
    var number: EditText? = null
    var search: EditText? = null

    var save: Button? = null
    var delete: Button? = null
    var userID: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.database_main, container, false)

        name = root.findViewById(R.id.name_editText)
        number = root.findViewById(R.id.year_editText)
        search = root.findViewById(R.id.search_editText)

        save = root.findViewById(R.id.btn_save)
        delete = root.findViewById(R.id.btn_delete)

        btn_search = root.findViewById(R.id.btn_search)
        databaseHelper = DatabaseHelper(requireContext())

        database = databaseHelper?.writableDatabase // получаем БД для чтения из DatabaseHelper
        recyclerView = root.findViewById(R.id.list)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        databaseAdapter = MyAdapterDatabase(fill())
        recyclerView?.adapter = databaseAdapter
        reloadRecyclerList()

        delete?.setOnClickListener {
            delete()
        }

        save?.setOnClickListener {
            save()
        }

        btn_search?.setOnClickListener {
            searching()
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

    private fun delete() {
        database?.execSQL("DELETE FROM ${DatabaseHelper.TABLE} WHERE ${DatabaseHelper.COLUMN_NUMBER} = ${number!!.text}")

        number!!.text.clear()
        name!!.text.clear()
        reloadRecyclerList()

    }

    private fun save() {
        val content = ContentValues()
        content.put(DatabaseHelper.COLUMN_NAME, name!!.text.toString())
        content.put(DatabaseHelper.COLUMN_NUMBER, number!!.text.toString().toInt())

        if (userID > 0)
            database?.update(DatabaseHelper.TABLE, content, DatabaseHelper.COLUMN_ID + "=" + userID.toString(), null)
        else
            database?.insert(DatabaseHelper.TABLE, null, content)

        number!!.text.clear()
        name!!.text.clear()
        reloadRecyclerList()
    }

    private fun searching() {
        val query = search!!.text.toString()

        if (query =="")
            reloadRecyclerList()
        else {
            val search = database?.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE} WHERE ${DatabaseHelper.COLUMN_NUMBER} = ?", arrayOf(query))
            val value = getList(search)
            databaseAdapter?.updateAdapter(value)
        }

    }

    private fun reloadRecyclerList() {
        val cursor = database?.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE}", null)
        val value = getList(cursor)
        databaseAdapter?.updateAdapter(value)
    }

    private fun getList(cursor: Cursor?): MutableList<DatabaseModel> {
        val list = mutableListOf<DatabaseModel>()
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val number = cursor.getInt(2)
                val desc = cursor.getString(1)

                val model = DatabaseModel(number, desc)
                list.add(model)
                cursor.moveToNext()
            }
            cursor.close()
        }
        return list
    }



}





















