package com.example.recyclerview.ui.DatabaseORM

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAODatabaseORM {

    @Insert
    fun insert(model: DatabaseORMModel)

    @Delete
    fun delete(model: DatabaseORMModel)

    @Query("DELETE FROM DatabaseORMModel WHERE number = :id")
    fun deleteByID (id: Int)

    @Query("SELECT * FROM DatabaseORMModel")
    fun selectAll(): MutableList<DatabaseORMModel>

    @Query("SELECT * FROM DatabaseORMModel WHERE number = :id")
    fun selectByID(id: Int): DatabaseORMModel

    @Query("SELECT * FROM DatabaseORMModel WHERE name LIKE :name")
    fun selectByName(name: String): MutableList<DatabaseORMModel>

}