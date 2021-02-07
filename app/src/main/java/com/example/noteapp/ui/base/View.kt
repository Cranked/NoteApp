package com.example.noteapp.ui.base

import android.content.Intent
import androidx.fragment.app.Fragment

interface View {
    fun showMessage(message: String)
    fun showFragment(fragment: Fragment)
    fun goToActivity(intent: Intent)
    fun showDialog(message: String)
}
