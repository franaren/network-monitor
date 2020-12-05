package com.arenas.conectivitymonitor

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

object NetworkDetectorLiveData : LiveData<Boolean>() {
    private val manager: ConnectivityManager by lazy {
        App.instance!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    private var connectionCnt = 0

    private val netCallback= object: ConnectivityManager.NetworkCallback(){

        override fun onAvailable(network: Network) {
            postValue(true)
            connectionCnt++
            super.onAvailable(network)
        }

        override fun onLost(network: Network) {
            connectionCnt--
            if (connectionCnt == 0) {
                postValue(false)
            }
            super.onLost(network)
        }
    }

    override fun onActive() {
        connectionCnt = 0
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            .build()
        manager.registerNetworkCallback(networkRequest, netCallback)
    }

    override fun onInactive() {
        connectionCnt = 0
        manager.unregisterNetworkCallback(netCallback)
    }

}