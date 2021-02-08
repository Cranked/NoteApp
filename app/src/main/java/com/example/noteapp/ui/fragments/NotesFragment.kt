package com.example.noteapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.noteapp.R
import com.example.noteapp.ui.main.MainActivity
import com.google.android.material.tabs.TabLayout

class NotesFragment(var mainActivity: MainActivity) : Fragment() {
    lateinit var viewPager: ViewPager
    lateinit var tabs: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        viewPager = view.findViewById(R.id.viewpager)
        tabs = view.findViewById(R.id.tabs)
        setupViewPager(viewPager)
        tabs.setupWithViewPager(viewPager)
        return view
    }

    fun setupViewPager(viewPager: ViewPager) {
        val viewPagerAdapter = ViewPagerAdapter(mainActivity.supportFragmentManager)
        viewPagerAdapter.addFrag(DoneNoteFragment(mainActivity), getString(R.string.doneNote))
        viewPagerAdapter.addFrag(
            ContinuousNoteFragment(mainActivity),
            getString(R.string.continousNote)
        )
        viewPagerAdapter.addFrag(TodoNoteFragment(mainActivity), getString(R.string.todoNote))
        viewPager.adapter = viewPagerAdapter

    }

    internal class ViewPagerAdapter(manager: FragmentManager?) :
        FragmentStatePagerAdapter(manager!!) {

        private val mFragmentList = arrayListOf<Fragment>()
        private val mFragmentTitleList = arrayListOf<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }

    }
}