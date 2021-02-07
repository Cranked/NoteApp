package com.example.noteapp.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picture")
class Picture {
    @PrimaryKey(autoGenerate = true)
    public var pictureId: Int = 0

    @ColumnInfo(name = "noteId")
    var noteId: Int = 0

    @ColumnInfo(name = "pictureName")
    lateinit var pictureName: String

    @ColumnInfo(name = "userId")
    var userId: Int = 0


}