package ru.geekbrains.filmapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData

class NetworkConnectionReceiver : BroadcastReceiver() {

    companion object {
        private val flag: MutableLiveData<Boolean> = MutableLiveData()
    }

    fun getState() = flag


    override fun onReceive(context: Context?, intent: Intent?) {
        when(checkInternet(context)){
            true-> flag.value = false
            false-> flag.value = true
        }
    }

    private fun checkInternet(context: Context?): Boolean {
        context?.let {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            activeNetwork?.let {
                return activeNetwork.isConnectedOrConnecting
            } ?: return false
        }?: return false
    }
}