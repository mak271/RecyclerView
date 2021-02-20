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
    var year: EditText? = null
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
//        val fr: Fragment = root.findViewById(R.id.nav_database)
//        fr.parentFragmentManager
//        fr.onPrimaryNavigationFragmentChanged(true)

        name = root.findViewById(R.id.name_editText)
        year = root.findViewById(R.id.year_editText)
        search = root.findViewById(R.id.search_editText)

        save = root.findViewById(R.id.btn_save)
        delete = root.findViewById(R.id.btn_delete)

        btn_search = root.findViewById(R.id.btn_search)
        databaseHelper = DatabaseHelper(requireContext())

        database = databaseHelper?.writableDatabase // получаем БД для чтения из DatabaseHelper
        userCursor = database?.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE}", null) // курсор делает запросы в БД
        recyclerView = root.findViewById(R.id.list)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        databaseAdapter = MyAdapterDatabase(userCursor!!, fill())
        recyclerView?.adapter = databaseAdapter




            userCursor = database?.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE} WHERE ${DatabaseHelper.COLUMN_ID} =?", arrayOf(userID.toString()))
            userCursor?.moveToFirst()
            //name?.setText(userCursor?.getString(1))
            //year?.setText(userCursor?.getInt(2).toString())
            userCursor?.close()


        delete?.setOnClickListener {

            delete()

        }

        save?.setOnClickListener {

            save()

        }

        btn_search?.setOnClickListener {
            recyclerView?.clearOnScrollListeners()
            searching()
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

    fun delete() {
        val t = year?.text.toString()
        database?.execSQL("DELETE FROM ${DatabaseHelper.TABLE} WHERE ${DatabaseHelper.COLUMN_NUMBER} = ${year!!.text}")
        reload()

    }

    fun save() {
        val content = ContentValues()
        content.put(DatabaseHelper.COLUMN_NAME, name!!.text.toString())
        content.put(DatabaseHelper.COLUMN_NUMBER, year!!.text.toString().toInt())

        if (userID > 0)
            database?.update(DatabaseHelper.TABLE, content, DatabaseHelper.COLUMN_ID + "=" + userID.toString(), null)
        else
            database?.insert(DatabaseHelper.TABLE, null, content)


        reload()
    }

    fun searching() {
        var query = search!!.text.toString()
        database?.rawQuery("SELECT ${DatabaseHelper.COLUMN_NAME} FROM ${DatabaseHelper.TABLE} WHERE ${DatabaseHelper.COLUMN_NUMBER} = $query", null)


        reload()
//        var cursor: Cursor = database?.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE} WHERE ${DatabaseHelper.COLUMN_YEAR} LIKE %${search!!.text}%", null)!!
//
//        if (cursor.count < 0)
//        {
//            if (cursor.moveToFirst()) {
//                do {
//                    recyclerView?.adapter = MyAdapter(cursor, fill())
//                } while (cursor.moveToNext())
//            }
//        }
//        else Toast.makeText(this, "mistake", Toast.LENGTH_SHORT).show()
//        cursor.close()
//        reload()

    }

    fun reload() {


        val intent = Intent(context, MainActivity()::class.java)
        startActivity(intent)
        database?.close()
    }




}





















