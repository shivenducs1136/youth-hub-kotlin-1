package com.dsckiet.youthhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.youthhub.R.id.fragmentContainerView
import com.example.youthhub.R.id.splash_logo_youthhub

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//         Enabled When Sigin Work is done
        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
//            navHostFragment.visibility = View.GONE
            finish()
        }, 3000)



    }
}