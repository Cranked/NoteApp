package com.example.noteapp

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

class Helper {

    fun validationNull(text: String): Boolean {
        return text.isEmpty()
    }

    fun hasPermission(activity: Activity?, permission: String?): Boolean {
        return (ActivityCompat.checkSelfPermission(activity!!, permission!!)
                === PackageManager.PERMISSION_GRANTED)
    }

}