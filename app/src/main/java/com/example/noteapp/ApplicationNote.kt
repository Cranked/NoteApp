package com.example.noteapp

import android.app.Application
import android.content.res.Configuration
import com.example.noteapp.data.db.DatabaseManager

class ApplicationNote : Application() {
    lateinit var dataBase: DatabaseManager
    override fun onCreate() {
        super.onCreate()
        dataBase = DatabaseManager.getDatabaseManager(this)!!

    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

}