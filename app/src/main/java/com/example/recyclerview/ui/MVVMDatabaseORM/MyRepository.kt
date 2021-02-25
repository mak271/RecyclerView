package com.example.recyclerview.ui.MVVMDatabaseORM

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyRepository {

    companion object {

        var ormDatabase: ORMDatabase? = null

        private fun initDB(context: Context): ORMDatabase {
            return ORMDatabase.getOrmDatabase(context)!!
        }

        fun insert(context: Context, model: DatabaseORMModel) {
            ormDatabase = initDB(context)

            GlobalScope.launch(Dispatchers.IO) {
                ormDatabase!!.daoDatabaseORM().insert(model)
            }
        }

        fun deleteById(context: Context, id: Int) {
            ormDatabase = initDB(context)
            GlobalScope.launch(Dispatchers.IO) {
                ormDatabase!!.daoDatabaseORM().deleteByID(id)
            }
        }

        fun selectAll(context: Context): LiveData<MutableList<DatabaseORMModel>> {
            ormDatabase = initDB(context)
            return ormDatabase!!.daoDatabaseORM().selectAll()
        }

        fun selectByName(context: Context, name: String): LiveData<MutableList<DatabaseORMModel>> {
            ormDatabase = initDB(context)
            return ormDatabase!!.daoDatabaseORM().selectByName(name)
        }

        fun selectById(context: Context, id: Int): LiveData<DatabaseORMModel> {
            ormDatabase = initDB(context)
            return ormDatabase!!.daoDatabaseORM().selectByID(id)
        }

    }

}