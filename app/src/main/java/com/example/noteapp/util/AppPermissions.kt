package com.example.noteapp.util

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

class AppPermissions(var mActivity: Activity) {

    private fun hasPermission(activity: Activity, permission: String): Boolean {
        return (ActivityCompat.checkSelfPermission(activity, permission) === PackageManager.PERMISSION_GRANTED)
    }

    fun hasPermission(activity: Activity,  permissions:Array<String>): Boolean {
        permissions.forEach{
            if (!hasPermission(activity, it)
            ) {
                return false
            }
        }
        return true
    }

    fun requestPermission(permission: String, requestCode: Int) {
        if (ActivityCompat.checkSelfPermission(mActivity, permission)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(mActivity, arrayOf(permission), requestCode);
        }
    }

    fun requestPermissions(mActivity: Activity, permissions:Array<String>, requestCode: Int) {
        permissions.forEach {
            if (!hasPermission(mActivity, it)) {
                ActivityCompat.requestPermissions(
                    mActivity,
                    arrayOf(it.toString()), requestCode
                )
            }
        }
    }
}