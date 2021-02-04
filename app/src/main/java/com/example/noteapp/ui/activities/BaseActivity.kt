package com.example.noteapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import com.example.noteapp.Helper
import com.example.noteapp.R
import com.example.noteapp.data.db.DatabaseManager
import com.example.noteapp.data.db.dao.NotesDao
import com.example.noteapp.data.db.dao.UserDao

open class BaseActivity : AppCompatActivity() {
    var database: DatabaseManager? = null
    lateinit var userDao: UserDao
    lateinit var notesDao: NotesDao
    lateinit var helper: Helper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        helper = Helper()
        database = DatabaseManager.getDatabaseManager(this)
        userDao = database!!.userDao()
        notesDao = database!!.notesDao()
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment)
            .commitAllowingStateLoss()
    }
}