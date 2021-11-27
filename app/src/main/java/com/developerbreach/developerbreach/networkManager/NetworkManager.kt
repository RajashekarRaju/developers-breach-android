package com.developerbreach.developerbreach.networkManager

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


/**
 * This class has method to perform network connection test.
 * Performs for Android devices Q and below.
 *
 * @param context needs to be declared to call system services.
 * @return if false no network is available.
 */
class NetworkManager(context: Context) {

    private val manager: ConnectivityManager? =
        ContextCompat.getSystemService(context, ConnectivityManager::class.java)

    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean>
        get() = _isConnected

    init {
        manager?.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                _isConnected.postValue(true)
            }

            override fun onLost(network: Network) {
                _isConnected.postValue(false)
            }
        })
    }

    fun checkForActiveNetwork(): Boolean {
        val activeNetwork: Network? = manager?.activeNetwork
        val capabilities: NetworkCapabilities? = manager?.getNetworkCapabilities(activeNetwork)
        return capabilities != null &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}