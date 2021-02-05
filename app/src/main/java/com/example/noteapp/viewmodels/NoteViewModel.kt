package com.example.noteapp.viewmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.noteapp.BR
import com.example.noteapp.data.db.models.Notes

class NoteViewModel(var mNotes: Notes) : BaseObservable() {

    var title: String?
        @Bindable
        get() = mNotes.title
        set(value) {
            mNotes.title = value
            notifyPropertyChanged(BR.title)
        }

    var subject: String?
        @Bindable
        get() = mNotes.subject
        set(value) {
            mNotes.subject = value
            notifyPropertyChanged(BR.subject)
        }

    var description: String?
        @Bindable
        get() = mNotes.description
        set(value) {
            mNotes.description = value
            notifyPropertyChanged(BR.description)
        }

}