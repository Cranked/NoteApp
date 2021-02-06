package com.example.noteapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.data.db.models.Notes

@Dao
interface NotesDao {
    @Query("Select * from notes where userId=:userId")
    fun getUserNotes(userId: Int): List<Notes>

    @Query("Select * from notes where noteId=:noteId")
    fun getNoteInformation(noteId: Int): List<Notes>

    @Query("Select * from notes where noteState=:noteState")
    fun getNoteList(noteState: Int): List<Notes>

    @Insert
    fun insert(notes: Notes)

    @Update
    fun update(notes: Notes)


}