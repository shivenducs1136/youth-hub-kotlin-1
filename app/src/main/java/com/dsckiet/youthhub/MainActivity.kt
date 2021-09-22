package com.dsckiet.youthhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.youthhub.databinding.ActivityMainBinding
import com.example.youthhub.ui.HomeFragment
import com.example.youthhub.ui.ProfileFragment
import com.example.youthhub.ui.SeachFragment
import com.example.youthhub.ui.SubrciptionFragment
import com.gauravk.bubblenavigation.BubbleNavigationLinearView
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentTransaction: FragmentTransaction

    private lateinit var home: BubbleNavigationLinearView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView : BubbleNavigationLinearView = findViewById(R.id.bottom_navigation_view_linear)
        val navController = findNavController(R.id.fragment_container)

        bottomNavigationView.setNavigationChangeListener{view, position ->

            when(position){
                0 -> {
                    navController.popBackStack()
                    navController.navigate(R.id.homeFragment)
                }
                1->{
                    navController.popBackStack()
                    navController.navigate(R.id.seachFragment)
                }
                2->{
                    navController.popBackStack()
                    navController.navigate(R.id.subrciptionFragment)
                }
                3->{
                    navController.popBackStack()
                    navController.navigate(R.id.profileFragment)
                }
            }
        }
    }

    }
