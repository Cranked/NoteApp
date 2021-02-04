package com.example.noteapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import com.example.noteapp.R
import com.example.noteapp.adapter.NoteAdapter
import com.example.noteapp.data.db.models.Notes
import com.example.noteapp.ui.fragments.NotesFragment
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Exception

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        showFragment(NotesFragment())
        getUserNotes()

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
}