package com.example.noteapp.ui.base

import androidx.fragment.app.Fragment

interface View {
    fun showMessage(message: String)
    fun showFragment(fragment: Fragment)

}