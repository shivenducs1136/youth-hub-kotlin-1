package com.example.youthhub.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.viewpager2.widget.ViewPager2
import com.dsckiet.youthhub.databinding.FragmentContinueWithGoogleBinding
import com.google.android.material.tabs.TabLayoutMediator



class Continue_With_GoogleFragment : Fragment() {

    private lateinit var binding: FragmentContinueWithGoogleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, com.dsckiet.youthhub.R.layout.fragment_continue__with__google,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var images = listOf(
            com.dsckiet.youthhub.R.drawable.frame_1,
            com.dsckiet.youthhub.R.drawable.frame_2,
            com.dsckiet.youthhub.R.drawable.frame_3,
        )

        val adapter = ViewPagerAdapter(images)
        binding.doppelgangerViewPager.adapter = adapter

        TabLayoutMediator(binding.tablayout,binding.doppelgangerViewPager, TabLayoutMediator.TabConfigurationStrategy({tab,position->

        })).attach()
        binding.doppelgangerViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
//                Snackbar.make(view,"You are selected"+{position+1},Snackbar.LENGTH_SHORT).show()
            }
        })
    }

}