package com.example.recyclerview.ui.MVVMDatabaseORM

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseORMModel(@PrimaryKey val number: Int, val name: String)