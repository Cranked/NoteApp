package com.example.noteapp.ui.main

import com.example.noteapp.data.db.models.Notes

interface MainView {
    fun logOut()
    fun showSelectedRow(notes: Notes)
}