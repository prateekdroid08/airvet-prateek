package com.softradix.airvet.ui.activities

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.softradix.airvet.R
import com.softradix.airvet.network.NetworkChangeReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mInterNetCheckReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mInterNetCheckReceiver =
            NetworkChangeReceiver()      // register check internet broadcast receiver
        @Suppress("DEPRECATION")
        registerReceiver(
            mInterNetCheckReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mInterNetCheckReceiver)      // unregister check internet broadcast receiver

    }
}