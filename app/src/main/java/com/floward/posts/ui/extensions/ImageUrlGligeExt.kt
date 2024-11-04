package com.floward.posts.ui.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

internal fun ImageView.setPhotoImageUrl(context: Context, url: String, defaultImage: Int?) {
    Glide.with(context)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                this@setPhotoImageUrl.setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                placeholder?.let { this@setPhotoImageUrl.setImageDrawable(it) }
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                if (defaultImage != null && defaultImage != 0)
                    this@setPhotoImageUrl.setImageResource(defaultImage)
                else
                    this@setPhotoImageUrl.setImageResource(0)
            }
        })
}
