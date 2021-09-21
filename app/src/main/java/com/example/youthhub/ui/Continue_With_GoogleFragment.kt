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

import me.relex.circleindicator.CircleIndicator3




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

        val indicator: CircleIndicator3 = view.findViewById(com.example.youthhub.R.id.bottom_dots_indicator)
        indicator.setViewPager(binding.doppelgangerViewPager)

    }

}
/*
private lateinit var binding: FragmentContinueWithGoogleBinding
var images = intArrayOf(
    R.drawable.frame_1,
    R.drawable.frame_2,
    R.drawable.frame_3,
)
var imagesname = arrayOf(
    "Frame 1",
    "Frame 2",
    "Frame 3"
)*/
//override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    super.onViewCreated(view, savedInstanceState)
//        binding.cwgCarouselView.pageCount = imagesname.size
//
//        binding.cwgCarouselView.setImageListener{
//                position, imageview->
//            imageview.setImageResource(images[position])
//        }
//        binding.cwgCarouselView.setImageClickListener { position ->
//            Toast.makeText(context, imagesname[position], Toast.LENGTH_SHORT).show()
//        }

//}
//    class DoppelgangerAdapter(activity: AppCompatActivity, val itemsCount: Int) :
//        FragmentStateAdapter(activity) {
//
//
//        override fun getItemCount(): Int {
//            return itemsCount
//        }
//
//        override fun createFragment(position: Int): Fragment {
//            return DoppelgangerFragment.getInstance(position)
//        }
//    }