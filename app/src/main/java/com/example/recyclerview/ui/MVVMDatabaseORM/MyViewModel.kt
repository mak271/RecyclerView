package com.example.recyclerview.ui.MVVMDatabaseORM

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MyViewModel(application: Application) : AndroidViewModel(application) {

    fun insert(context: Context, model: DatabaseORMModel) {
        MyRepository.insert(context, model)
    }

    fun deleteById(context: Context, id: Int) {
        MyRepository.deleteById(context, id)
    }

    fun selectAll(context: Context): LiveData<MutableList<DatabaseORMModel>> {
        return MyRepository.selectAll(context)
    }

    fun selectByName(context: Context, name: String): LiveData<MutableList<DatabaseORMModel>> {
        return MyRepository.selectByName(context, name)
    }

    fun selectById(context: Context, id: Int): LiveData<DatabaseORMModel> {
        return MyRepository.selectById(context, id)
    }

}