package com.softradix.airvet.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.view.Window
import android.view.WindowManager
import com.softradix.airvet.R

object Utills {

    var mActiveConnectionIdChatRoom: String? = ""
    var mProgressDialog: Dialog? = null
    var selectedSubsID = ""

    @Suppress("DEPRECATION")
    fun isNetworkAvailable(context: Activity?): Boolean {
        val service = context?.getSystemService(Context.CONNECTIVITY_SERVICE)
        if (service != null) {
            val cm = service as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            if (netInfo != null && netInfo.isConnectedOrConnecting) {
                return true
            } else {
                context.toast(context.resources.getString(R.string.no_internet_body))
            }
        }
        return false
    }

    fun showInternetDialog(context: Context) {
        mProgressDialog = Dialog(context, android.R.style.Theme_Translucent)
        mProgressDialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        mProgressDialog?.setContentView(R.layout.dialog_internet)
        mProgressDialog?.setCancelable(false)
        mProgressDialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        mProgressDialog?.window?.statusBarColor = Color.parseColor("#80000000")
        mProgressDialog?.show()
    }


    fun hideInternetDialog() {
        if (mProgressDialog != null && mProgressDialog?.isShowing == true) {
            mProgressDialog?.dismiss()
        }
    }


}