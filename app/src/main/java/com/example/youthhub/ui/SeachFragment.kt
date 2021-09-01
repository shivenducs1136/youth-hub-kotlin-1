package com.example.youthhub.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.youthhub.R
import com.example.youthhub.databinding.FragmentPlaylistBinding
import com.example.youthhub.databinding.FragmentSeachBinding


class SeachFragment : Fragment() {

    private lateinit var binding: FragmentSeachBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_seach,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchBackBtn.setOnClickListener{

            findNavController().navigate(R.id.action_seachFragment_to_homeFragment)

        }
    }

}