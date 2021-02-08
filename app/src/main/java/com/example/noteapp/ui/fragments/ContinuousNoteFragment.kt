package com.example.noteapp.ui.fragments

import android.content.Intent
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
import com.example.noteapp.enums.NoteOperation
import com.example.noteapp.models.IntegratedNoteModel
import com.example.noteapp.ui.deletenote.UpdateActivity
import com.example.noteapp.ui.main.MainActivity

class ContinuousNoteFragment(val mainActivity: MainActivity) : Fragment(),SelectedItemListener {
    lateinit var adapter: NoteAdapter
    lateinit var notesList: List<Notes>
    lateinit var recyclerView: RecyclerView
    var activeUserId: Int? = 0
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
        activeUserId = mainActivity.userDao.getActivateUser(true).userId
        notesList= mainActivity.notesDao.getNoteList(1,activeUserId!!)
         adapter = NoteAdapter(notesList, context!!,this)
        recyclerView.adapter = adapter
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
                mainActivity.pictureDao.getNotePictures(notes.noteId, activeUserId!!).forEach {
                    imageList.add(it.pictureName)
                }
                val integratedView =
                    mainActivity.helper.getView(context!!, integratedList, imageList!!)
                var dialog = mainActivity.getDialog("Bilgi", true)
                dialog.setView(integratedView)
                dialog.show()

            }
            NoteOperation.DELETE -> {
                var dialog =
                    mainActivity.getDialog(getString(R.string.wantedDeleteSelectedRow), false)
                dialog.setNegativeButton(getString(R.string.no)) { dialog, which ->
                    dialog.dismiss()
                    mainActivity.noteOperation = NoteOperation.SHOW
                }
                dialog.setPositiveButton(getString(R.string.yes)) { dialog, which ->
                    mainActivity.notesDao.deleteNotesFromUser(notes.noteId, activeUserId!!)
                    mainActivity.pictureDao.deletePictureId(notes.noteId, activeUserId!!)
                    notesList = mainActivity.notesDao.getNoteList(1, activeUserId!!)
                    adapter = NoteAdapter(notesList, context!!, this)
                    recyclerView.adapter = adapter
                    dialog.dismiss()
                }
                dialog.show()
            }
            NoteOperation.UPDATE -> {
                val intent = Intent(context, UpdateActivity::class.java)
                intent.putExtra("noteId", notes.noteId)
                mainActivity.startActivity(intent)

            }
        }
    }
}