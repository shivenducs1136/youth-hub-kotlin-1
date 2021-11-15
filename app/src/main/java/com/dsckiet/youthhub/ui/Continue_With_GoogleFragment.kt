package com.example.youthhub.ui

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.dsckiet.youthhub.MainActivity
import com.dsckiet.youthhub.R
import com.dsckiet.youthhub.databinding.FragmentContinueWithGoogleBinding
import com.example.youthhub.ui.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


const val RC_SIGN_IN = 100
class Continue_With_GoogleFragment : Fragment() {

    private lateinit var binding: FragmentContinueWithGoogleBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, com.dsckiet.youthhub.R.layout.fragment_continue__with__google,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createRequest()
        auth = Firebase.auth
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
            }
        })

        binding.btnSignInButton.setOnClickListener {
            signIn()
        }


    } private fun createRequest() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(ContentValues.TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(requireContext(),"Sign In Failed Please Try Again ", Toast.LENGTH_SHORT).show()
                // Google Sign In failed, update UI appropriately
                Log.w(ContentValues.TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    Toast.makeText(requireContext(),"SigningIn as ${GoogleSignIn.getLastSignedInAccount(requireContext()).displayName}", Toast.LENGTH_SHORT).show()
                    val i = Intent(requireActivity(),MainActivity::class.java)
                    startActivity(i)
                    requireActivity().finish()
                } else {
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)

                }
            }
    }

}