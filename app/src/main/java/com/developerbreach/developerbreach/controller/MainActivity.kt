package com.developerbreach.developerbreach.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.databinding.ActivityMainBinding
import com.developerbreach.developerbreach.fcm.setNotificationChannel
import com.developerbreach.developerbreach.networkManager.NetworkManager


class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // NavigationController to set default NavHost as nav_host_fragment.
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            onDestinationChanged(destination)
        }

        // Creates a notification channel based on SDK level
        setNotificationChannel(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        val connected = NetworkManager(applicationContext).isConnected
        connected.observe(this, { available ->
            if (!available) {
                AppNavDirections(navController).toNetworkFragment()
            }
        })
    }
}