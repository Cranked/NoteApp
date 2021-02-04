package com.example.noteapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.adapter.NoteAdapter
import com.example.noteapp.data.db.models.Notes
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Exception

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUserNotes()

    }

    fun getUserNotes() {
        try {
            userDao.getAllUser().forEach {
                if (it.isActivated) {
                    recyclerview.layoutManager = LinearLayoutManager(this)
                    recyclerview.adapter = NoteAdapter(getActivatedUserNotes(it.userId), this)
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