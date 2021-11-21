package com.developerbreach.developerbreach.controller

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.databinding.ActivityMainBinding
import com.developerbreach.developerbreach.view.network.NetworkManager


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // NavigationController to set default NavHost as nav_host_fragment.
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        navController.addOnDestinationChangedListener { controller, destination, _ ->
            onDestinationChanged(destination, controller)
        }

        // Creates a notification channel based on version
        setNotificationChannel()
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

    private fun setNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            val channelId = getString(R.string.default_notification_channel_id)
            val channelName = getString(R.string.default_notification_channel_name)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(
                NotificationChannel(
                    channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW
                )
            )
        }
    }
}