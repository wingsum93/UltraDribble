package com.ericho.ultradribble.extension

import android.graphics.Bitmap
import android.support.v7.graphics.Palette
import android.widget.ImageView
import com.bumptech.glide.Priority
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.ericho.ultradribble.R
import com.ericho.ultradribble.glide.GlideApp
import com.ericho.ultradribble.glide.OnPaletteProcessingCallback

/**
 * Created by steve_000 on 12/9/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.extension
 */

fun ImageView.loadNormal(url: String) {
    if (url.endsWith(".gif")) {
        GlideApp.with(this.context)
                .asGif()
                .load(url)
                .placeholder(R.drawable.bg_shot)
                .centerCrop()
                .error(R.drawable.bg_shot)
                .into(this)
    } else {
        GlideApp.with(this.context)
                .asBitmap()
                .load(url)
                .placeholder(R.drawable.bg_shot)
                .centerCrop()
                .error(R.drawable.bg_shot)
                .into(this)
    }
}

fun ImageView.loadAvatar(url: String) {
    GlideApp.with(this.context)
            .asBitmap()
            .placeholder(R.drawable.ic_avatar_placeholder)
            .load(url)
            .circleCrop()
            .error(R.drawable.ic_avatar_placeholder)
            .into(this)
}

fun ImageView.loadHighQualityWithPalette(url: String, callback: OnPaletteProcessingCallback) {
    if (url.endsWith(".gif")) {
        GlideApp.with(this.context)
                .asGif()
                .load(url)
                .placeholder(R.drawable.bg_shot)
                .centerCrop()
                .error(R.drawable.bg_shot)
                .priority(Priority.HIGH)
                .into(this)
    } else {
        GlideApp.with(this.context)
                .asBitmap()
                .load(url)
                .placeholder(R.drawable.bg_shot)
                .thumbnail(0.5f)
                .centerCrop()
                .error(R.drawable.bg_shot)
                .priority(Priority.HIGH)
                .into(object : BitmapImageViewTarget(this) {

                    // The function [onResourceReady] will called twice during one loading process.
                    override fun onResourceReady(resource: Bitmap?, transition: Transition<in Bitmap>?) {
                        super.onResourceReady(resource, transition)
                        resource?.let {
                            // The maximum color count is higher, the time of palette processing is more.
                            Palette.from(it).maximumColorCount(4).generate { palette ->
                                callback.OnPaletteGenerated(palette)
                            }
                        } ?: run {
                            callback.OnPaletteNotAvailable()
                        }
                    }
                })
    }
}