package com.dsckiet.youthhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth


class SplashScreen : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        firebaseAuth = FirebaseAuth.getInstance()

//         Enabled When Sigin Work is done
        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            if(firebaseAuth.currentUser != null)
                finish()
//            navHostFragment.visibility = View.GONE

        }, 3000)



    }
}