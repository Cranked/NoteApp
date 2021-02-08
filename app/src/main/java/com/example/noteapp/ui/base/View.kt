package com.example.noteapp.ui.base

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

interface View {
    fun showMessage(message: String)
    fun showFragment(fragment: Fragment)
    fun goToActivity(intent: Intent)
    fun getDialog(message: String,cancelable:Boolean):AlertDialog.Builder
}
