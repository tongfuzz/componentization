package com.joyukc.lib_imageloader.listener

import android.graphics.drawable.Drawable
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.target.Target
import com.joyukc.lib_imageloader.options.DataSource

/**
 *
 * @author tongfu
 * @date 2022/10/26
 * @email suntongfu@joyuai.com
 * @desc 图片加载结果监听
 *
 */
interface CompleteListener<T> {

    fun onLoadFailed(
        e: Exception?,
        model: Any?,
        isFirstResource: Boolean
    ): Boolean

    fun onResourceReady(
        resource: T?,
        model: Any?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean

    fun onLoadFailed() {

    }
}