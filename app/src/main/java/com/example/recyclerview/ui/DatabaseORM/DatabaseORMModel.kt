package com.example.recyclerview.ui.DatabaseORM

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseORMModel(@PrimaryKey val number: Int, val name: String)