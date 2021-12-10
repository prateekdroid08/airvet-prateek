package com.softradix.airvet.utils

import android.app.Activity
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.softradix.airvet.R

fun ImageView.loadImageWithoutShimmer(url: String = "") {

    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 2f
    circularProgressDrawable.centerRadius = 20f
    circularProgressDrawable.start()
    Glide.with(context)
        .load(url)
        .centerCrop()
        .placeholder(circularProgressDrawable)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.ic_placeholder_home)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                circularProgressDrawable.stop()
                return false

            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                circularProgressDrawable.stop()
                return false
            }
        })
        .into(this)
}

// show toast on activity
fun Activity.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

// show toast on Fragment
fun Fragment.toast(message: String?) {
    Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
}