package com.android.payback.myapplication.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import javax.inject.Inject

class ImageLoader @Inject constructor(
    private val context: Context
) {

    fun load(preLoadUrl: String?=null, url: String?, imageView: ImageView?) {
        if (url != null && imageView != null) {
            Glide.with(context).load(
                url
            ).thumbnail(
                Glide.with(context)
                    .load(preLoadUrl)
            )
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }

    }




}