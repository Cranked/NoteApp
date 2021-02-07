package com.example.noteapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.adapter.NoteAdapter
import com.example.noteapp.adapter.SelectedItemListener
import com.example.noteapp.data.db.models.Notes
import com.example.noteapp.ui.main.MainActivity

class ContinuousNoteFragment(val mainActivity: MainActivity) : Fragment(),SelectedItemListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_continuous_note, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.continuousNoteList)
        val mDivider = ContextCompat.getDrawable(context!!, R.drawable.divider)
        val vItemDecoration = DividerItemDecoration(
            context!!,
            DividerItemDecoration.VERTICAL
        )
        vItemDecoration.setDrawable(mDivider!!)
        recyclerView.addItemDecoration(vItemDecoration)
        val layoutManager = LinearLayoutManager(mainActivity)
        recyclerView.layoutManager = layoutManager
        var notes = mainActivity.notesDao.getNoteList(1,mainActivity.activeUserId!!)
        val adapter = NoteAdapter(notes, context!!,this)
        recyclerView.adapter = adapter
        return view
    }

    override fun selectedRow(notes: Notes) {

    }
}