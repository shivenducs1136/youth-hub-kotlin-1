package com.example.youthhub.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dsckiet.youthhub.MainActivity
import com.dsckiet.youthhub.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.dsckiet.youthhub.databinding.FragmentContinueWithGoogleBinding
import com.google.android.material.tabs.TabLayoutMediator



class Continue_With_GoogleFragment : Fragment() {

    private lateinit var binding: FragmentContinueWithGoogleBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_continue__with__google,
            container,
            false
        )
        firebaseAuth = FirebaseAuth.getInstance()
        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("899736584107-67d6hbtbvr9ciggkgtjka5849uaetn8n.apps.googleusercontent.com")
            .requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(),googleSignInOptions)
        binding = DataBindingUtil.inflate(inflater, com.dsckiet.youthhub.R.layout.fragment_continue__with__google,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignInButton.setOnClickListener(View.OnClickListener {
            var intent = googleSignInClient.signInIntent
            startActivityForResult(intent,100)
        })
        var images = listOf(
            R.drawable.frame_1,
            com.dsckiet.youthhub.R.drawable.frame_2,
            com.dsckiet.youthhub.R.drawable.frame_3,
        )

        val adapter = ViewPagerAdapter(images)
        binding.doppelgangerViewPager.adapter = adapter

        TabLayoutMediator(
            binding.tablayout,
            binding.doppelgangerViewPager,
            TabLayoutMediator.TabConfigurationStrategy({ tab, position ->

            })
        ).attach()
        binding.doppelgangerViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
//                Snackbar.make(view,"You are selected"+{position+1},Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            val signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            if (signInAccountTask.isSuccessful) {
                displayToast("Successful signin!")

                try {
                    val googleSignInAccount = signInAccountTask.getResult(
                        ApiException::class.java
                    )
                    if (googleSignInAccount != null) {
                        val authCredential =
                            GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
                        firebaseAuth.signInWithCredential(authCredential)
                            .addOnCompleteListener(this.requireActivity(),
                                OnCompleteListener<AuthResult> { task ->
                                    if (task.isSuccessful) {
                                        displayToast("Firebase Auth Successful")
                                        val i = Intent(requireContext(),MainActivity::class.java)
                                        startActivity(i)
                                        requireActivity().finish()
                                    } else {
                                        displayToast("Firebase Auth Failed! " + (task.exception?.message))
                                    }
                                })
                    }
                } catch (e: ApiException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun displayToast(s: String) {
        Toast.makeText(this.context, s, Toast.LENGTH_SHORT).show()
    }
}