package com.project.laundrykotlin.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val fragment = InputFragment()
                fragment.arguments = Bundle().apply {
                }
                fragment
            }
            1 -> {
                val fragment = DetailFragment()
                fragment.arguments = Bundle().apply {
                }
                fragment
            }
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}