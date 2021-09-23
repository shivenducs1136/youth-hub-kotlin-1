package com.dsckiet.youthhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.dsckiet.youthhub.R
import com.gauravk.bubblenavigation.BubbleNavigationLinearView

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
