package com.example.noteapp.ui.main

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.noteapp.R
import com.example.noteapp.data.db.models.Notes
import com.example.noteapp.ui.base.BaseActivity
import com.example.noteapp.ui.fragments.NotesFragment
import com.example.noteapp.ui.fragments.dialogfragments.AddNoteFragment
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
        showFragment(NotesFragment())
        setViewFabs(visibleState)

    }

    fun setOnClickListener() {
        noteFab.setOnClickListener {
            visibleState = !visibleState
            setViewFabs(visibleState)
        }
        addNoteFab.setOnClickListener {


            showFragment(AddNoteFragment(this))
        }
    }

    fun showalertdialog(){
        val myDialog: AlertDialog.Builder = AlertDialog.Builder(this)

// Set icon and title

// Set icon and title
        myDialog.setTitle(title)
        myDialog.setView(R.layout.fragment_add_note)
// set up buttons

// set up buttons
        myDialog.setPositiveButton(
            "kaydet",
            DialogInterface.OnClickListener { dialog, whichButton ->
                Toast.makeText(this, "kaydet", Toast.LENGTH_SHORT).show()
            })
        myDialog.setNegativeButton(getString(R.string.cancel),
            DialogInterface.OnClickListener { dialog, whichButton ->
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            })

// return the created Dialog


// return the created Dialog
        myDialog.create()
        myDialog.show()
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