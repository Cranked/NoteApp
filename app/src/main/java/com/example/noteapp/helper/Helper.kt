package com.example.noteapp

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import androidx.core.app.ActivityCompat
import java.io.File

class Helper {

    fun validationNull(text: String): Boolean {
        return text.isEmpty()
    }

    fun hasPermission(activity: Activity?, permission: String?): Boolean {
        return (ActivityCompat.checkSelfPermission(activity!!, permission!!)
                === PackageManager.PERMISSION_GRANTED)
    }
     fun getPhotoFile(context:Context,fileName: String): File {
        val directoryStorage = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", directoryStorage)
    }


}