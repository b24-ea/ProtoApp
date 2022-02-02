package com.aldanmaz.skyq.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.aldanmaz.skyq.model.Images
import com.aldanmaz.skyq.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.downloadFromUrl(url: List<Images>, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeholderProgressBar(context : Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()

    }
}

