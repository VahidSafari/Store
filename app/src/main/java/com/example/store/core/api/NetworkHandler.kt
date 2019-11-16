package com.example.store.core.api

import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import com.example.store.App

class NetworkHandler constructor(val context: App) {
    fun hasNetworkConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}