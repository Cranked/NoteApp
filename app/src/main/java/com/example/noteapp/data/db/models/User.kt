package com.example.noteapp.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0

    @ColumnInfo(name = "userName")
    lateinit var userName: String

    @ColumnInfo(name = "password")
    lateinit var password: String

    @ColumnInfo(name = "IsActivated")
    var isActivated: Boolean=false


}