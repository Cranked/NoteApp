package com.example.noteapp

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Environment
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.noteapp.data.db.models.Notes
import com.example.noteapp.models.IntegratedNoteModel
import java.io.File
import kotlin.math.acos

class Helper {

    fun validationNull(text: String): Boolean {
        return text.isEmpty()
    }

    fun hasPermission(activity: Activity?, permission: String?): Boolean {
        return (ActivityCompat.checkSelfPermission(activity!!, permission!!)
                === PackageManager.PERMISSION_GRANTED)
    }

    fun getPhotoFile(context: Context, fileName: String): File {
        val directoryStorage = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", directoryStorage)
    }

    fun getView(context: Context, notes: List<IntegratedNoteModel>, imageList: List<String>): View {
        var layout = LinearLayout(context)
        var horizontalScrollView = HorizontalScrollView(context)
        layout.orientation = LinearLayout.VERTICAL

        val textViewLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val linearLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        val seperateLayoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2)

        layout.layoutParams = linearLayoutParams



        notes.forEach {
            var cnTextView = TextView(context)
            var valueTextView = TextView(context)

            val seperateLinearLayout = LinearLayout(context)

            seperateLinearLayout.layoutParams = seperateLayoutParams
            seperateLinearLayout.background = context.getDrawable(R.color.black)

            cnTextView.layoutParams = textViewLayoutParams
            valueTextView.layoutParams = textViewLayoutParams

            cnTextView.text = it.let { it.columnName }
            valueTextView.text = it.let { it.value }
            layout.addView(cnTextView)
            layout.addView(valueTextView)
            layout.addView(seperateLinearLayout)

        }
        val view = getViewFromPhoto(context, imageList)
        horizontalScrollView.addView(view)
        layout.addView(horizontalScrollView)
        return layout
    }

    fun getViewFromPhoto(context: Context, images: List<String>): View {
        val linearLayout = LinearLayout(context)
        val linearLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        linearLayoutParams.setMargins(15, 15, 15, 15)
        linearLayout.layoutParams = linearLayoutParams
        val imageViewLayoutParams = LinearLayout.LayoutParams(150, 150)
        images.forEach {
            val imageView = ImageView(context)
            val takenPhoto = BitmapFactory.decodeFile(it)
            imageView.layoutParams = imageViewLayoutParams
            imageView.setImageBitmap(takenPhoto)
            linearLayout.addView(imageView)
        }
        return linearLayout
    }

}