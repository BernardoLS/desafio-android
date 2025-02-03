package com.picpay.desafio.android.core.extensions

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.picpay.desafio.android.core.enums.ImageType

fun ImageView.bindImageWithListener(
    context: Context,
    url: String? = null,
    placeHolder: Int,
    roundingRadius: Int = 8,
    imageType: ImageType? = ImageType.NORMAL,
    onResourceReady: () -> Unit,
    onLoadFailed: () -> Unit) {

    if (context is Activity && context.isDestroyed || url.isNullOrEmpty()) {
        return
    }

    val requestOptions = when (imageType) {
        ImageType.CIRCLE -> RequestOptions.circleCropTransform()
        ImageType.ROUNDED -> RequestOptions().transform(CenterCrop(), RoundedCorners(roundingRadius))
        else -> RequestOptions()
    }

    Glide.with(context)
        .load(url)
        .apply(requestOptions)
        .placeholder(placeHolder)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadFailed()
                return false            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onResourceReady()
                return false
            }
        })
        .into(this)
}