package com.softradix.airvet.base

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.softradix.airvet.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {
    private var mProgressDialog: Dialog? = null
    private var mActivity: FragmentActivity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity
    }

    // method overloading
    fun callFragment(view: View, fragmentId: Int, bundle: Bundle) {
        view.findNavController().navigate(fragmentId, bundle)
    }

    fun callFragment(view: View, fragmentId: Int, entryAnim: Int, bundle: Bundle) {
        val navBuilder = NavOptions.Builder()
        navBuilder.setEnterAnim(entryAnim).setPopEnterAnim(R.anim.fade_in)
            .setPopExitAnim(R.anim.fade_out).setPopUpTo(fragmentId, true)
        view.findNavController().navigate(fragmentId, bundle, navBuilder.build())
    }

    fun showLoading(show: Boolean?) {
        if (show == true) showProgress() else hideProgress()
    }

    private fun showProgress() {
        if (mProgressDialog == null) {
            mActivity?.let {
                mProgressDialog = Dialog(it, android.R.style.Theme_Translucent)
                mProgressDialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
                mProgressDialog?.setContentView(R.layout.loader_layout)
                mProgressDialog?.setCancelable(false)
            }
        }

        mProgressDialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        mProgressDialog?.window?.statusBarColor = Color.parseColor("#80000000")
        mProgressDialog?.show()
    }


    private fun hideProgress() {
        if (mProgressDialog != null && mProgressDialog?.isShowing == true) {
            mProgressDialog?.dismiss()
        }
    }


}