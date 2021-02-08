package com.example.noteapp.adapter

import com.example.noteapp.data.db.models.Notes

interface SelectedItemListener {
    fun selectedRow(notes: Notes)
}