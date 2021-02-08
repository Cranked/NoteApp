package com.example.noteapp.ui.deletenote

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.example.noteapp.BuildConfig
import com.example.noteapp.R
import com.example.noteapp.data.db.models.Notes
import com.example.noteapp.data.db.models.Picture
import com.example.noteapp.databinding.ActivityUpdateBinding
import com.example.noteapp.ui.base.BaseActivity
import com.example.noteapp.ui.main.MainActivity
import java.io.File

class UpdateActivity : BaseActivity() {
    private val TAG: String = UpdateActivity::class.java.simpleName

    lateinit var binding: ActivityUpdateBinding

    var imageButton: ImageView? = null
    lateinit var cameraLinearLayout: LinearLayout
    lateinit var notesList: List<Notes>
    var pictureList = arrayListOf<String>()

    var noteId: Int? = 0
    private val REQUEST_CODE = 13
    private lateinit var filePhoto: File
    private val FILE_NAME = "photo.jpg"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)
        binding.updateNoteActivity = this
        activeUserId = userDao.getActivateUser(true).userId
        cameraLinearLayout = findViewById(R.id.cameraLinearLayout)
        val bundle = intent.extras
        if (bundle != null) {
            noteId = bundle.getInt("noteId")
            notesList = notesDao.getNoteInformation(noteId!!, activeUserId!!)
            notesList.forEach {
                binding.titleEditText.setTextKeepState(it.title)
                binding.subjectEditText.setTextKeepState(it.subject)
                binding.descriptionEditText.setTextKeepState(it.description)
                binding.stateSpinner.setSelection(it.noteState)
            }
            val notePictureList = pictureDao.getNotePictures(noteId!!, activeUserId!!)
            notePictureList.forEach {
                setNotesImages(it.pictureName)
            }
        }
        notesList.forEach {
            binding.titleEditText.setTextKeepState(it.title)
            binding.subjectEditText.setTextKeepState(it.subject)
            binding.descriptionEditText.setTextKeepState(it.description)
            binding.stateSpinner.setSelection(it.noteState)
        }
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
                setNotesImages(filePhoto.absolutePath)
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

    fun updateNote(view: View) {
        try {
            var notes = Notes()
            notes.noteId = noteId!!
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
            val activeUserId = userDao.getActivateUser(true).userId
            notes.userId = activeUserId
            notesDao.update(notes)

            pictureList.forEach {
                if (pictureDao.getpictureNameCount(it) == 0) {
                    val images = Picture()
                    images.userId = activeUserId
                    images.pictureName = it
                    images.noteId = notes.noteId
                    pictureDao.insert(images)
                }
            }
            Toast.makeText(this, getString(R.string.successRegistered), Toast.LENGTH_SHORT)
                .show()
            pictureList.clear()
            goToActivity(Intent(this, MainActivity::class.java))
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    fun setNotesImages(fileName: String) {

        val takenPhoto = BitmapFactory.decodeFile(fileName)
        val imageView = ImageView(this)
        imageView.layoutParams = LinearLayout.LayoutParams(150, 150)
        imageView.tag = "Resim"
        val closeImageView = ImageView(this)
        closeImageView.layoutParams =
            LinearLayout.LayoutParams(50, LinearLayout.LayoutParams.WRAP_CONTENT)
        closeImageView.setImageDrawable(getDrawable(R.drawable.icon_close))
        imageView.setImageBitmap(takenPhoto)
        pictureList.add(fileName)
        binding.cameraLinearLayout.addView(imageView)
        binding.cameraLinearLayout.addView(closeImageView)
        closeImageView.setOnClickListener {
            pictureList.remove(fileName)
            pictureDao.deleteWithPictureName(fileName)
            binding.cameraLinearLayout.removeView(imageView)
            binding.cameraLinearLayout.removeView(closeImageView)
        }

    }

    fun cancel(view: View) {
        try {
            goToActivity(Intent(this, MainActivity::class.java))
        } catch (e: Exception) {
            Log.e("Hata", e.toString())
        }
    }


    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}