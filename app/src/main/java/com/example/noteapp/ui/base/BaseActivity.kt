package com.example.noteapp.ui.base

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.noteapp.Helper
import com.example.noteapp.ApplicationNote
import com.example.noteapp.R
import com.example.noteapp.data.db.DatabaseManager
import com.example.noteapp.data.db.dao.NotesDao
import com.example.noteapp.data.db.dao.PictureDao
import com.example.noteapp.data.db.dao.UserDao
import com.example.noteapp.util.AppPermissions
import java.lang.Exception

open class BaseActivity : AppCompatActivity(), View {
    var database: DatabaseManager? = null
    var activeUserId: Int? = 0
    lateinit var userDao: UserDao
    lateinit var notesDao: NotesDao
    lateinit var pictureDao: PictureDao
    lateinit var helper: Helper
    lateinit var appPermissions: AppPermissions
    lateinit var appNote: ApplicationNote
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        appNote = application as ApplicationNote
        helper = Helper()
        appPermissions = AppPermissions(this)
        database = appNote.dataBase
        userDao = database!!.userDao()
        notesDao = database!!.notesDao()
        pictureDao = database!!.pictureDao()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment)
            .commitAllowingStateLoss()
    }

    override fun goToActivity(intent: Intent) {
        startActivity(intent)
        finish()
    }

    fun checkPermissions() {
        try {
            val permissions =
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            appPermissions = AppPermissions(this)
            if (!appPermissions.hasPermission(this, permissions)) {

                appPermissions.requestPermissions(
                    this,
                    permissions,
                    0
                )
            }
        } catch (e: Exception) {
            showMessage(e.toString())
        }
    }

    override fun getDialog(message: String, cancelable: Boolean): AlertDialog.Builder {
        var builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.app_name))
        builder.setCancelable(cancelable)
        builder.setMessage(message)
        return builder

    }
}