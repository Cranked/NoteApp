package com.example.noteapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.noteapp.R
import com.example.noteapp.data.db.models.Notes
import com.example.noteapp.ui.base.BaseActivity
import com.example.noteapp.ui.addnote.AddNoteActivity
import com.example.noteapp.ui.fragments.NotesFragment
import com.example.noteapp.ui.login.LoginActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(NotesFragment(this))

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addNote -> {
                goToActivity(Intent(this, AddNoteActivity::class.java))
            }
            R.id.logOut -> {
                userDao.getActivateUser(true).let {
                    it.isActivated = false
                    userDao.update(it)
                    goToActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun goToActivity(intent: Intent) {
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.appbar_menu, menu)
        return true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


}