package com.example.noteapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.adapter.NoteAdapter
import com.example.noteapp.ui.main.MainActivity


class DoneNoteFragment(val mainActivity: MainActivity) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_done_note, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.doneNoteList)
        val layoutManager = LinearLayoutManager(mainActivity)
        recyclerView.layoutManager = layoutManager
        val mDivider = ContextCompat.getDrawable(context!!, R.drawable.divider)
        val vItemDecoration = DividerItemDecoration(
            context!!,
            DividerItemDecoration.VERTICAL
        )
        vItemDecoration.setDrawable(mDivider!!)
        recyclerView.addItemDecoration(vItemDecoration)
        var notes = mainActivity.notesDao.getNoteList(0)
        val adapter = NoteAdapter(notes, context!!)
        recyclerView.adapter = adapter
        // Inflate the layout for this fragment
        return view

    }


}