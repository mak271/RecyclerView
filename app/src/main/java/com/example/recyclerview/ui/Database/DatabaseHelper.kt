package com.example.recyclerview.ui.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.ImageView
import com.example.recyclerview.R
import com.squareup.picasso.Picasso

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, SCHEMA) {

    companion object {
        private const val DATABASE_NAME = "cats.db"
        private const val SCHEMA = 1

        const val TABLE = "cats"


        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "name"
        const val COLUMN_NUMBER = "year"
    }



    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE $TABLE ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT, $COLUMN_NUMBER INTEGER)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS $TABLE")
        onCreate(db)

    }

}