package com.example.noteapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.noteapp.Helper
import com.example.noteapp.R
import com.example.noteapp.data.db.DatabaseManager

open class BaseActivity : AppCompatActivity() {
    var database: DatabaseManager? = null
    lateinit var helper: Helper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        helper = Helper()
        database = DatabaseManager.getDatabaseManager(this)

    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}