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
    lateinit var title: String

    @ColumnInfo(name = "subject")
    lateinit var subject: String

    @ColumnInfo(name = "description")
    lateinit var description: String

    @ColumnInfo(name = "noteState")
    var noteState: Int = 0
}