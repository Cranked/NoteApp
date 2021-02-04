package com.example.noteapp.ui.main

import android.Manifest
import android.os.Bundle
import android.view.View
import com.example.noteapp.R
import com.example.noteapp.data.db.models.Notes
import com.example.noteapp.ui.base.BaseActivity
import com.example.noteapp.ui.fragments.NotesFragment
import com.example.noteapp.util.AppPermissions
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : BaseActivity() {

    private var visibleState: Boolean = false
    var filePath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment(NotesFragment())
        setViewFabs(visibleState)
        noteFab.setOnClickListener {
            visibleState = !visibleState
            setViewFabs(visibleState)
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