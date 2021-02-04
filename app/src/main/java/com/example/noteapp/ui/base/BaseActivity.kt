package com.example.noteapp.ui.base

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.noteapp.Helper
import com.example.noteapp.R
import com.example.noteapp.data.db.DatabaseManager
import com.example.noteapp.data.db.dao.NotesDao
import com.example.noteapp.data.db.dao.UserDao

open class BaseActivity : AppCompatActivity(), View {
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

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment)
            .commitAllowingStateLoss()
    }
}