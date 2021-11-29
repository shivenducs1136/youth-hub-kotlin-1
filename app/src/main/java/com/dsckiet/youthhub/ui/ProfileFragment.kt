package com.example.youthhub.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.dsckiet.youthhub.R
import com.dsckiet.youthhub.databinding.FragmentProfileBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignIn.getLastSignedInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    lateinit var binding:FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var signInAccount: GoogleSignInAccount = GoogleSignIn.getLastSignedInAccount(this.requireContext())
        if(signInAccount!=null){
            binding.userName.text = signInAccount.displayName
            Log.e("NAME",signInAccount.displayName)
            Log.e("EMAIL",signInAccount.email)
            Log.e("URL",signInAccount.photoUrl.toString())
            Picasso.with(context)
                .load(signInAccount.photoUrl)
                .into(binding.profilePic)
            binding.userEmail.text = signInAccount.email
            binding.userEmail.text="ufhdni"
        }
    binding.settingsFab.setOnClickListener {
        findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
       }
    }

}