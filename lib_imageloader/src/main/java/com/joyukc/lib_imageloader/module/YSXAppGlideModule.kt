package com.joyukc.lib_imageloader.module

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.joyukc.lib_imageloader.config.Configuration

/**
 *
 * @author tongfu
 * @date 2022/10/26
 * @email suntongfu@joyuai.com
 * @desc 自定义glide组件
 *
 */
@GlideModule
class YSXAppGlideModule : AppGlideModule() {

    companion object {
        const val TAG = "AppGlideModule"
    }


    override fun applyOptions(context: Context, builder: GlideBuilder) {
        //super.applyOptions(context, builder)
        Log.i(TAG, "appliOptions")
        //设置缓存
        builder.setMemoryCache(Configuration.memoryCache)
            .setDiskCache(Configuration.getDiskCache(context))
    }

//    //添加okhttp支持
//    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
//        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory())
//    }
}