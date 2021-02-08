package com.example.noteapp.data.db.dao

import androidx.room.*
import com.example.noteapp.data.db.models.Notes

@Dao
interface NotesDao {
    @Query("Select * from notes where userId=:userId")
    fun getUserNotes(userId: Int): List<Notes>

    @Query("Select * from notes where noteId=:noteId AND userId=:userId")
    fun getNoteInformation(noteId: Int, userId: Int): List<Notes>

    @Query("Select * from notes where noteState=:noteState AND userId=:userId ORDER BY noteId DESC")
    fun getNoteList(noteState: Int, userId: Int): List<Notes>

    @Query("Select MAX(noteId) from notes")
    fun getMaxValue(): Int

    @Query("Delete from notes where noteId=:noteId AND userId=:userId")
    fun deleteNotesFromUser(noteId: Int, userId: Int)

    @Insert
    fun insert(notes: Notes)

    @Update
    fun update(notes: Notes)

    @Delete
    fun delete(notes: Notes)

}