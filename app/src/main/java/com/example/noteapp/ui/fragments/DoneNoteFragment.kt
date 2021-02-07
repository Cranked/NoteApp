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
import com.example.noteapp.enums.NoteOperation
import com.example.noteapp.R
import com.example.noteapp.adapter.NoteAdapter
import com.example.noteapp.adapter.SelectedItemListener
import com.example.noteapp.data.db.models.Notes
import com.example.noteapp.models.IntegratedNoteModel
import com.example.noteapp.ui.main.MainActivity


class DoneNoteFragment(val mainActivity: MainActivity) : Fragment(), SelectedItemListener {

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
        val activeUserId=mainActivity.userDao.getActivateUser(true).userId
        var notes = mainActivity.notesDao.getNoteList(0,activeUserId)
        val adapter = NoteAdapter(notes, context!!, this)
        recyclerView.adapter = adapter
        // Inflate the layout for this fragment
        return view

    }

    override fun selectedRow(notes: Notes) {
        when (mainActivity.noteOperation) {
            NoteOperation.SHOW -> {
                var integratedList = arrayListOf<IntegratedNoteModel>()
                var titleIntegratedModel =
                    IntegratedNoteModel(getString(R.string.title), notes.let { notes.title!! })
                var subjectIntegratedModel =
                    IntegratedNoteModel(getString(R.string.subject), notes.let { notes.subject!! })
                var descriptionIntegratedModel = IntegratedNoteModel(
                    getString(R.string.description),
                    notes.let { notes.description!! })

                integratedList.add(titleIntegratedModel)
                integratedList.add(subjectIntegratedModel)
                integratedList.add(descriptionIntegratedModel)
                var imageList = arrayListOf<String>()
                val activeUserId: Int = mainActivity.userDao.getActivateUser(true).userId
                mainActivity.pictureDao.getNotePictures(notes.noteId,activeUserId).forEach {
                    imageList.add(it.pictureName)
                }
                val integratedView =
                    mainActivity.helper.getView(context!!, integratedList, imageList!!)
                var dialog = mainActivity.getDialog("Bilgi", true)
                dialog.setView(integratedView)
                dialog.show()

            }
        }
    }
}