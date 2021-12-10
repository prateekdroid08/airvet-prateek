package com.softradix.airvet.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.softradix.airvet.utils.Utills


class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val status: String? = NetworkUtil.getConnectivityStatusString(context)
        if (status == "3") {
            Utills.showInternetDialog(context)
        } else {
            Utills.hideInternetDialog()
//            Toast.makeText(context, "Please swipe refresh the page.", Toast.LENGTH_SHORT).show()
        }
    }
}