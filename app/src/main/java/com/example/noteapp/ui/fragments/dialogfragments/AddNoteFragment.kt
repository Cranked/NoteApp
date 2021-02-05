package com.example.noteapp.ui.fragments.dialogfragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentAddNoteBinding

class AddNoteFragment : Fragment() {
    lateinit var binding: FragmentAddNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.setContentView(activity!!, R.layout.fragment_add_note)
        binding.addNoteFragment = this

        val view = inflater.inflate(R.layout.fragment_add_note, container, false)
        return view
    }

    fun takePhoto() {



    }
}