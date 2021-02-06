package com.example.noteapp.ui.addnote

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.example.noteapp.BuildConfig
import com.example.noteapp.R
import com.example.noteapp.data.db.models.Notes
import com.example.noteapp.data.db.models.Picture
import com.example.noteapp.databinding.ActivityAddNoteBinding
import com.example.noteapp.ui.base.BaseActivity
import com.example.noteapp.ui.main.MainActivity
import java.io.File

class AddNoteActivity : BaseActivity() {
    private val TAG: String = AddNoteActivity::class.java.simpleName
    lateinit var binding: ActivityAddNoteBinding
    var imageButton: ImageView? = null
    lateinit var cameraLinearLayout: LinearLayout
    var pictureList = arrayListOf<String>()
    private val REQUEST_CODE = 13
    private lateinit var filePhoto: File
    private val FILE_NAME = "photo.jpg"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)
        binding.addNoteActivity = this


        cameraLinearLayout = findViewById(R.id.cameraLinearLayout)

    }

    fun takePhoto() {
        val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        filePhoto = helper.getPhotoFile(this, FILE_NAME)


        val providerFile = FileProvider.getUriForFile(
            this,
            BuildConfig.APPLICATION_ID + ".provider",
            filePhoto
        )
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerFile)
        if (takePhotoIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePhotoIntent, REQUEST_CODE)
        } else {
            Toast.makeText(this, "Camera could not open", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                val takenPhoto = BitmapFactory.decodeFile(filePhoto.absolutePath)
                val imageButton = ImageView(this)
                imageButton.layoutParams = LinearLayout.LayoutParams(150, 150)
                imageButton.tag = "Resim"
                val closeImageView = ImageView(this)
                closeImageView.layoutParams =
                    LinearLayout.LayoutParams(50, LinearLayout.LayoutParams.WRAP_CONTENT)
                closeImageView.setImageDrawable(getDrawable(R.drawable.icon_close))
                imageButton.setImageBitmap(takenPhoto)
                pictureList.add(filePhoto.path)
                binding.cameraLinearLayout.addView(imageButton)
                binding.cameraLinearLayout.addView(closeImageView)
                closeImageView.setOnClickListener {
                    pictureList.remove(filePhoto.path)

                    binding.cameraLinearLayout.removeView(imageButton)
                    binding.cameraLinearLayout.removeView(closeImageView)
                }

            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
            if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                imageButton?.setImageURI(data?.data)
            }
        } catch (e: Exception) {
            Log.e("Hata", e.toString())
            showMessage(e.toString())
        }
    }

    fun saveNote(view: View) {
        try {
            var notes = Notes()
            notes.title = binding.titleEditText.text.toString()
            notes.subject = binding.subjectEditText.text.toString()
            notes.description = binding.descriptionEditText.text.toString()
            when (binding.stateSpinner.selectedItem) {
                getString(R.string.doneNote) -> {
                    notes.noteState = 0
                }
                getString(R.string.continousNote) -> {
                    notes.noteState = 1
                }
                getString(R.string.todoNote) -> {
                    notes.noteState = 2
                }
            }
            notesDao.insert(notes)
            pictureList.forEach {
                val images = Picture()
                images.pictureName = it
                images.noteId = notes.noteId
                pictureDao.insert(images)
            }
            Toast.makeText(this, getString(R.string.successRegistered), Toast.LENGTH_SHORT)
                .show()
            pictureList.clear()
            onBackPressed()
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }


    fun cancel(view: View) {
        try {
            onBackPressed()

        } catch (e: Exception) {
            Log.e("Hata", e.toString())
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}