package com.example.noteapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.noteapp.R
import com.example.noteapp.data.db.models.Notes
import com.example.noteapp.ui.base.BaseActivity
import com.example.noteapp.ui.addnote.AddNoteActivity
import com.example.noteapp.ui.fragments.NotesFragment
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


class MainActivity : BaseActivity() {

    private var visibleState: Boolean = false
    var filePath: String = ""
    lateinit var noteFab: ExtendedFloatingActionButton
    lateinit var addNoteFab: ExtendedFloatingActionButton
    lateinit var deleteNoteFab: ExtendedFloatingActionButton
    lateinit var updateNoteFab: ExtendedFloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteFab = findViewById(R.id.noteFab)
        addNoteFab = findViewById(R.id.addNoteFab)
        deleteNoteFab = findViewById(R.id.deleteNoteFab)
        updateNoteFab = findViewById(R.id.updateNoteFab)
        setOnClickListener()
        setViewFabs(visibleState)
        showFragment(NotesFragment(this))

    }

    fun setOnClickListener() {
        noteFab.setOnClickListener {
            visibleState = !visibleState
            setViewFabs(visibleState)
        }
        addNoteFab.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
            finish()
        }
    }

    fun getActivatedUserNotes(userId: Int): List<Notes> {
        return notesDao.getUserNotes(userId)
    }

    fun setViewFabs(visible: Boolean) {
        if (visible) {
            addNoteFab.visibility = View.VISIBLE
            updateNoteFab.visibility = View.VISIBLE
            deleteNoteFab.visibility = View.VISIBLE
        } else {
            deleteNoteFab.visibility = View.GONE
            updateNoteFab.visibility = View.GONE
            addNoteFab.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


}