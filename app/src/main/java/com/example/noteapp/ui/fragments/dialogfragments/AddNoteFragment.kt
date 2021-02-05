package com.example.noteapp.ui.fragments.dialogfragments

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
import androidx.fragment.app.Fragment
import com.example.noteapp.BuildConfig
import com.example.noteapp.R
import com.example.noteapp.data.db.models.Notes
import com.example.noteapp.data.db.models.Picture
import com.example.noteapp.databinding.FragmentAddNoteBinding
import com.example.noteapp.ui.base.BaseActivity
import java.io.File

class AddNoteFragment(mActivity: BaseActivity) : Fragment() {
    private val TAG: String = AddNoteFragment::class.java.simpleName
    lateinit var binding: FragmentAddNoteBinding
    var imageButton: ImageView? = null
    lateinit var cameraLinearLayout: LinearLayout
    var mActivity = mActivity
    var pictureList = arrayListOf<String>()
    private val REQUEST_CODE = 13
    private lateinit var filePhoto: File
    private val FILE_NAME = "photo.jpg"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.setContentView(activity!!, R.layout.fragment_add_note)
        binding.addNoteFragment = this

        val view = inflater.inflate(R.layout.fragment_add_note, container, false)
        cameraLinearLayout = view.findViewById(R.id.cameraLinearLayout)
        return view
    }

    fun takePhoto() {
        val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        filePhoto = mActivity.helper.getPhotoFile(context!!, FILE_NAME)


        val providerFile = FileProvider.getUriForFile(mActivity, BuildConfig.APPLICATION_ID + ".provider", filePhoto)
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerFile)
        if (takePhotoIntent.resolveActivity(mActivity.packageManager) != null) {
            startActivityForResult(takePhotoIntent, REQUEST_CODE)
        } else {
            Toast.makeText(context, "Camera could not open", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                val takenPhoto = BitmapFactory.decodeFile(filePhoto.absolutePath)
                val imageButton = ImageView(context)
                imageButton.layoutParams = LinearLayout.LayoutParams(150, 150)
                imageButton.tag = "Resim"
                val closeImageView = ImageView(context)
                closeImageView.layoutParams =
                    LinearLayout.LayoutParams(50, LinearLayout.LayoutParams.WRAP_CONTENT)
                closeImageView.setImageDrawable(context!!.getDrawable(R.drawable.icon_close))
                imageButton.setImageBitmap(takenPhoto)
                pictureList.add(filePhoto.path)
                binding.cameraLinearLayout.addView(imageButton)
                binding.cameraLinearLayout.addView(closeImageView)
                closeImageView.setOnClickListener {
                    pictureList.remove(filePhoto.path)
                    pictureList.forEach {
                        Log.i("Image", it)
                    }
                    binding.cameraLinearLayout.removeView(imageButton)
                    binding.cameraLinearLayout.removeView(closeImageView)
                }
                pictureList.forEach {
                    Log.i("Image", it)
                }

            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
            if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                Toast.makeText(context, "foto sonuc", Toast.LENGTH_SHORT).show()
                imageButton?.setImageURI(data?.data)
            }
        } catch (e: Exception) {
            Log.e("Hata", e.toString())
            mActivity.showMessage(e.toString())
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
            mActivity.notesDao.insert(notes)
            pictureList.forEach {
                val images = Picture()
                images.pictureName = it
                images.noteId = notes.noteId
                mActivity.pictureDao.insert(images)
            }
            Toast.makeText(context, getString(R.string.successRegistered), Toast.LENGTH_SHORT).show()
            pictureList.clear()
            mActivity.onBackPressed()
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }


    fun cancel(view: View) {
        mActivity.onBackPressed()
    }

}