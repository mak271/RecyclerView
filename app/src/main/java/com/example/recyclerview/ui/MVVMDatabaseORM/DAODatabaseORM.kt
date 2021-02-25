package com.example.recyclerview.ui.MVVMDatabaseORM

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAODatabaseORM {

    @Insert
    fun insert(model: DatabaseORMModel)

    @Query("DELETE FROM DatabaseORMModel WHERE number = :id")
    fun deleteByID (id: Int)

    @Query("SELECT * FROM DatabaseORMModel")
    fun selectAll(): LiveData<MutableList<DatabaseORMModel>>

    @Query("SELECT * FROM DatabaseORMModel WHERE number = :id")
    fun selectByID(id: Int): LiveData<DatabaseORMModel>

    @Query("SELECT * FROM DatabaseORMModel WHERE name LIKE :name")
    fun selectByName(name: String): LiveData<MutableList<DatabaseORMModel>>

}