package com.example.youthhub.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.youthhub.databinding.FragmentContinueWithGoogleBinding
import com.example.youthhub.databinding.FragmentSeachBinding
import android.R
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

import me.relex.circleindicator.CircleIndicator3
import me.relex.circleindicator.SnackbarBehavior


class Continue_With_GoogleFragment : Fragment() {

    private lateinit var binding: FragmentContinueWithGoogleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, com.example.youthhub.R.layout.fragment_continue__with__google,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var images = listOf(
            com.example.youthhub.R.drawable.frame_1,
            com.example.youthhub.R.drawable.frame_2,
            com.example.youthhub.R.drawable.frame_3,
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