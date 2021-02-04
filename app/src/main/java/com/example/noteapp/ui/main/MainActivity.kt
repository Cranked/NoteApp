package com.example.noteapp.ui.main

import android.os.Bundle
import android.view.View
import com.example.noteapp.R
import com.example.noteapp.data.db.models.Notes
import com.example.noteapp.ui.base.BaseActivity
import com.example.noteapp.ui.fragments.NotesFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : BaseActivity() {

    private var visibleState: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment(NotesFragment())
        getUserNotes()
        setViewFabs(visibleState)
        noteFab.setOnClickListener {
            visibleState = !visibleState
            setViewFabs(visibleState)
        }
    }

    fun getUserNotes() {
        try {
            userDao.getAllUser().forEach {
                if (it.isActivated) {

                }
            }
        } catch (e: Exception) {
            showMessage(e.toString())
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

}