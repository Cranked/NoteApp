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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TodoNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodoNoteFragment(var mainActivity: MainActivity) : Fragment(),SelectedItemListener{


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_todo_note, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.todoNoteList)
        val mDivider = ContextCompat.getDrawable(context!!, R.drawable.divider)
        val vItemDecoration = DividerItemDecoration(
            context!!,
            DividerItemDecoration.VERTICAL
        )

        vItemDecoration.setDrawable(mDivider!!)
        recyclerView.addItemDecoration(vItemDecoration)
        val layoutManager = LinearLayoutManager(mainActivity)
        recyclerView.layoutManager = layoutManager
        var notes = mainActivity.notesDao.getNoteList(2,mainActivity.activeUserId!!)
        val adapter = NoteAdapter(notes, context!!,this)
        recyclerView.adapter = adapter

        return view
    }

    override fun selectedRow(notes: Notes) {
        TODO("Not yet implemented")
    }
}