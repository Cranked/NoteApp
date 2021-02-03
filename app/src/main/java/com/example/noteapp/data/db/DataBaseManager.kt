package com.example.noteapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.data.db.dao.NotesDao
import com.example.noteapp.data.db.dao.UserDao
import com.example.noteapp.data.db.models.Notes
import com.example.noteapp.data.db.models.User

@Database(entities = [User::class,Notes::class], version = 1)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun notesDao():NotesDao

    companion object {
        var INSTANCE: DatabaseManager?=null

        fun getDatabaseManager(context: Context): DatabaseManager? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseManager::class.java,
                    "note-app-database"
                ).allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
    fun destroyDataBase(){
        INSTANCE
    }
}