package com.ericho.ultradribble.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import java.io.InputStream

/**
 * Created by steve_000 on 12/9/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble
 */
@GlideModule
class MyGlideModule : AppGlideModule(){
    companion object {
        // Max cache size of glide.
        @JvmField
        val MAX_CACHE_SIZE = 1024 * 1024 * 512 // 512M

        // The cache directory name.
        @JvmField
        val CACHE_FILE_NAME = "IMG_CACHE" // cache file dir name

    }

    override fun applyOptions(context: Context?, builder: GlideBuilder?) {
        builder?.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
        // 36MB, memory cache size
        // default value: 24MB
        val memoryCacheSize = 1024 * 1024 * 36
        builder?.setMemoryCache(LruResourceCache(memoryCacheSize))

        // Internal cache
        builder?.setDiskCache(InternalCacheDiskCacheFactory(context, CACHE_FILE_NAME, MAX_CACHE_SIZE))
    }

    override fun isManifestParsingEnabled(): Boolean = false
    override fun registerComponents(context: Context?, glide: Glide?, registry: Registry?) {
        super.registerComponents(context, glide, registry)
        // Replace the http connection with okhttp
        registry?.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory())
    }
}