package com.dsckiet.youthhub

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.youthhub.ui.Continue_With_GoogleFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth


class SplashScreen : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var signInAccount: GoogleSignInAccount
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        firebaseAuth = FirebaseAuth.getInstance()
//         Enabled When Sigin Work is done
        val cont = findViewById<FragmentContainerView>(R.id.sfragmentContainerView)
           cont.visibility = View.GONE
        val logo = findViewById<ImageView>(R.id.splash_logo)
        logo.visibility = View.VISIBLE
        if(GoogleSignIn.getLastSignedInAccount(this) == null){
            cont.visibility = View.VISIBLE
            logo.visibility = View.GONE
            supportFragmentManager.beginTransaction().replace(R.id.sfragmentContainerView,
                Continue_With_GoogleFragment()
            )
        }
        else
            splashfun()

    }

    private fun splashfun() {
        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 3000)

    }
}