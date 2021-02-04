package com.example.noteapp.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class Notes {
    @PrimaryKey(autoGenerate = true)
    var noteId: Int = 0

    @ColumnInfo(name = "userId")
    var userId: Int = 0

    @ColumnInfo(name = "title")
     var title: String?=null

    @ColumnInfo(name = "subject")
     var subject: String?=null

    @ColumnInfo(name = "description")
     var description: String?=null

    @ColumnInfo(name = "noteState")
    var noteState: Int = 0
}