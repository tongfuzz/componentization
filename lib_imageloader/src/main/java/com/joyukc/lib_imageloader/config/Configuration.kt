package com.joyukc.lib_imageloader.config

import android.content.Context
import com.bumptech.glide.load.engine.cache.DiskCache
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import java.io.File

/**
 *
 * @author tongfu
 * @date 2022/10/26
 * @email suntongfu@joyuai.com
 * @desc glide的配置数据
 *
 */
object Configuration {

    //内存缓存大小
    val memoryCache by lazy {
        LruResourceCache(1024 * 1024 * 20)
    }

    //磁盘缓存大小
    private const val diskCacheSize = 250 * 1024 * 1024L

    //缓存目录
    private const val diskCacheDirName = "image_disk_cache"

    /**
     * 获取磁盘缓存工厂类
     *
     * @param context
     * @return
     */
    fun getDiskCache(context: Context): DiskCache.Factory {
        val cacheDirectory = File(context.cacheDir, diskCacheDirName)
        return DiskLruCacheFactory(cacheDirectory.absolutePath, diskCacheSize)
    }
}