package com.joyukc.lib_imageloader

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.joyukc.lib_imageloader.options.CacheStrategy
import com.joyukc.lib_imageloader.options.DisplayOptions
import com.joyukc.lib_imageloader.options.SourceType
import com.joyukc.lib_imageloader.options.TransitionOptions
import java.io.File
import java.net.URL

/**
 *
 * @author tongfu
 * @date 2022/10/27
 * @email suntongfu@joyuai.com
 * @desc 图片加载框架
 *
 */
class ImageLoader private constructor(private val requestManager: RequestManager) {
    private var requestBuilder: RequestBuilder<*>? = null

    companion object {
        fun with(activity: FragmentActivity) = ImageLoader(Glide.with(activity))
        fun with(fragment: Fragment) = ImageLoader(Glide.with(fragment))
        fun with(context: Context) = ImageLoader(Glide.with(context))
        fun clearCache(context: Context) = Glide.get(context).clearDiskCache()
    }

    /**
     * 下载图片作为文件，注意此方法必须在子线程中调用
     *
     * @param url 图片路径
     * @return 图片文件，可能为null
     */
    fun downloadFile(url: String?): File? {
        return requestManager.downloadOnly().load(url).submit().get()
    }

    /**
     * 下载图片作为Bitmap对象，注意此方法必须在子线程中调用
     *
     * @param url 图片路径
     * @param width  指定bitmap宽度
     * @param height 指定bitmap高度
     * @return
     */
    fun downloadBitmap(url: String?, width: Int = 0, height: Int = 0): Bitmap? {
        if (width <= 0 || height <= 0) {
            return requestManager.asBitmap().load(url).centerCrop().submit(SIZE_ORIGINAL, SIZE_ORIGINAL).get()
        }
        return requestManager.asBitmap().load(url).centerCrop().submit(width, height).get()
    }

    /**
     * 加载图片
     *
     * @param model 数据源
     * @param sourceType 目标源类型
     * @param displayOptions 展示配置 圆角，展位图等在此配置
     * @param transitionOptions 转换配置，淡入淡出等效果在此配置
     * @param cacheStrategy 缓存策略
     * @return
     */
    @SuppressLint("CheckResult")
    fun load(
        model: Bitmap?,
        sourceType: SourceType = SourceType.DRAWABLE,
        displayOptions: DisplayOptions? = DisplayOptions(),
        transitionOptions: TransitionOptions? = TransitionOptions(),
        cacheStrategy: CacheStrategy = CacheStrategy.AUTOMATIC,
    ): ImageLoader {
        val builder = gerRequestBuilder(sourceType)
        requestBuilder = builder
        builder.load(model)
        display(displayOptions, builder, sourceType, transitionOptions, cacheStrategy)
        return this
    }

    /**
     * 加载图片
     *
     * @param model 数据源
     * @param sourceType 目标源类型
     * @param displayOptions 展示配置 圆角，展位图等在此配置
     * @param transitionOptions 转换配置，淡入淡出等效果在此配置
     * @param cacheStrategy 缓存策略
     * @return
     */
    @SuppressLint("CheckResult")
    fun load(
        model: Drawable?,
        sourceType: SourceType = SourceType.DRAWABLE,
        displayOptions: DisplayOptions? = DisplayOptions(),
        transitionOptions: TransitionOptions? = TransitionOptions(),
        cacheStrategy: CacheStrategy = CacheStrategy.AUTOMATIC

    ): ImageLoader {
        val builder = gerRequestBuilder(sourceType)
        requestBuilder = builder
        builder.load(model)
        display(displayOptions, builder, sourceType, transitionOptions, cacheStrategy)
        return this
    }

    /**
     * 加载图片
     *
     * @param model 数据源
     * @param sourceType 目标源类型
     * @param displayOptions 展示配置 圆角，展位图等在此配置
     * @param transitionOptions 转换配置，淡入淡出等效果在此配置
     * @param cacheStrategy 缓存策略
     * @return
     */
    @SuppressLint("CheckResult")
    fun load(
        model: String?,
        sourceType: SourceType = SourceType.DRAWABLE,
        displayOptions: DisplayOptions? = DisplayOptions(),
        transitionOptions: TransitionOptions? = TransitionOptions(),
        cacheStrategy: CacheStrategy = CacheStrategy.AUTOMATIC

    ): ImageLoader {
        val builder = gerRequestBuilder(sourceType)
        requestBuilder = builder
        builder.load(model)
        display(displayOptions, builder, sourceType, transitionOptions, cacheStrategy)
        return this
    }

    /**
     * 加载图片
     *
     * @param model 数据源
     * @param sourceType 目标源类型
     * @param displayOptions 展示配置 圆角，展位图等在此配置
     * @param transitionOptions 转换配置，淡入淡出等效果在此配置
     * @param cacheStrategy 缓存策略
     * @return this
     */
    @SuppressLint("CheckResult")
    fun load(
        model: Uri?,
        sourceType: SourceType = SourceType.DRAWABLE,
        displayOptions: DisplayOptions? = DisplayOptions(),
        transitionOptions: TransitionOptions? = TransitionOptions(),
        cacheStrategy: CacheStrategy = CacheStrategy.AUTOMATIC

    ): ImageLoader {
        val builder = gerRequestBuilder(sourceType)
        requestBuilder = builder
        builder.load(model)
        display(displayOptions, builder, sourceType, transitionOptions, cacheStrategy)
        return this
    }

    /**
     * 加载图片
     *
     * @param model 数据源
     * @param sourceType 目标源类型
     * @param displayOptions 展示配置 圆角，展位图等在此配置
     * @param transitionOptions 转换配置，淡入淡出等效果在此配置
     * @param cacheStrategy 缓存策略
     * @return this
     */
    @SuppressLint("CheckResult")
    fun load(
        model: File?,
        sourceType: SourceType = SourceType.DRAWABLE,
        displayOptions: DisplayOptions? = DisplayOptions(),
        transitionOptions: TransitionOptions? = TransitionOptions(),
        cacheStrategy: CacheStrategy = CacheStrategy.AUTOMATIC

    ): ImageLoader {
        val builder = gerRequestBuilder(sourceType)
        requestBuilder = builder
        builder.load(model)
        display(displayOptions, builder, sourceType, transitionOptions, cacheStrategy)
        return this
    }

    /**
     * 加载图片
     *
     * @param model 数据源
     * @param sourceType 目标源类型
     * @param displayOptions 展示配置 圆角，展位图等在此配置
     * @param transitionOptions 转换配置，淡入淡出等效果在此配置
     * @param cacheStrategy 缓存策略
     * @return this
     */
    @SuppressLint("CheckResult")
    fun load(
        model: Int?,
        sourceType: SourceType = SourceType.DRAWABLE,
        displayOptions: DisplayOptions? = DisplayOptions(),
        transitionOptions: TransitionOptions? = TransitionOptions(),
        cacheStrategy: CacheStrategy = CacheStrategy.AUTOMATIC

    ): ImageLoader {
        val builder = gerRequestBuilder(sourceType)
        requestBuilder = builder
        builder.load(model)
        display(displayOptions, builder, sourceType, transitionOptions, cacheStrategy)
        return this
    }

    /**
     * 加载图片
     *
     * @param model 数据源
     * @param sourceType 目标源类型
     * @param displayOptions 展示配置 圆角，展位图等在此配置
     * @param transitionOptions 转换配置，淡入淡出等效果在此配置
     * @param cacheStrategy 缓存策略
     * @return this
     */
    @SuppressLint("CheckResult")
    fun load(
        model: URL?,
        sourceType: SourceType = SourceType.DRAWABLE,
        displayOptions: DisplayOptions? = DisplayOptions(),
        transitionOptions: TransitionOptions? = TransitionOptions(),
        cacheStrategy: CacheStrategy = CacheStrategy.AUTOMATIC

    ): ImageLoader {
        val builder = gerRequestBuilder(sourceType)
        requestBuilder = builder
        builder.load(model)
        display(displayOptions, builder, sourceType, transitionOptions, cacheStrategy)
        return this
    }

    /**
     * 加载图片
     *
     * @param model 数据源
     * @param sourceType 目标源类型
     * @param displayOptions 展示配置 圆角，展位图等在此配置
     * @param transitionOptions 转换配置，淡入淡出等效果在此配置
     * @param cacheStrategy 缓存策略
     * @return this
     */
    @SuppressLint("CheckResult")
    fun load(
        model: ByteArray?,
        sourceType: SourceType = SourceType.DRAWABLE,
        displayOptions: DisplayOptions? = DisplayOptions(),
        transitionOptions: TransitionOptions? = TransitionOptions(),
        cacheStrategy: CacheStrategy = CacheStrategy.AUTOMATIC

    ): ImageLoader {
        val builder = gerRequestBuilder(sourceType)
        requestBuilder = builder
        builder.load(model)
        display(displayOptions, builder, sourceType, transitionOptions, cacheStrategy)
        return this
    }

    /**
     * 加载图片
     *
     * @param model 数据源
     * @param sourceType 目标源类型
     * @param displayOptions 展示配置 圆角，展位图等在此配置
     * @param transitionOptions 转换配置，淡入淡出等效果在此配置
     * @param cacheStrategy 缓存策略
     * @return this
     */
    @SuppressLint("CheckResult")
    fun load(
        model: Any?,
        sourceType: SourceType = SourceType.DRAWABLE,
        displayOptions: DisplayOptions? = DisplayOptions(),
        transitionOptions: TransitionOptions? = TransitionOptions(),
        cacheStrategy: CacheStrategy = CacheStrategy.AUTOMATIC,

        ): ImageLoader {
        val builder = gerRequestBuilder(sourceType)
        requestBuilder = builder
        builder.load(model)
        display(displayOptions, builder, sourceType, transitionOptions, cacheStrategy)
        return this
    }

    /**
     * 加载图片
     *
     * @param model 数据源
     * @param sourceType 目标源类型
     * @param displayOptions 展示配置 圆角，展位图等在此配置
     * @param transitionOptions 转换配置，淡入淡出等效果在此配置
     * @param cacheStrategy 缓存策略
     * @return this
     */
    @SuppressLint("CheckResult")
    private fun display(
        displayOptions: DisplayOptions?,
        requestBuilder: RequestBuilder<out Any>,
        sourceType: SourceType,
        transitionOptions: TransitionOptions?,
        cacheStrategy: CacheStrategy
    ): RequestBuilder<out Any> {
        //设置展示效果
        displayOptions?.also {
            getRequestOptions(it).let { requestOptions ->
                requestBuilder.apply(requestOptions)
            }
        }

        transitionOptions?.also {
            applyTransition(requestBuilder, sourceType, it)
        }
        //设置缓存策略
        requestBuilder.diskCacheStrategy(
            when (cacheStrategy) {
                CacheStrategy.ALL -> DiskCacheStrategy.ALL
                CacheStrategy.DATA -> DiskCacheStrategy.DATA
                CacheStrategy.RESOURCE -> DiskCacheStrategy.RESOURCE
                CacheStrategy.AUTOMATIC -> DiskCacheStrategy.AUTOMATIC
                CacheStrategy.NONE -> DiskCacheStrategy.NONE
            }
        )
        return requestBuilder
    }

    /**
     * 加载图片到imageview中
     *
     * @param target
     */
    fun into(
        target: ImageView,
    ) {
        if (requestBuilder == null) {
            throw IllegalStateException("you must call load() before invoke this method")
        }
        kotlin.runCatching {
            requestBuilder!!.into(target)
        }
    }

    /**
     * 根据不同的目标类型，创建不同的requestBuilder
     *
     * @param sourceType 要加载的目标类型
     * @return
     */
    private fun gerRequestBuilder(sourceType: SourceType): RequestBuilder<out Any> {
        val builder = when (sourceType) {
            SourceType.DRAWABLE -> requestManager.asDrawable()
            SourceType.BITMAP -> requestManager.asBitmap()
            SourceType.GIF -> requestManager.asGif()
            SourceType.FILE -> requestManager.asFile()
        }
        return builder
    }

    /**
     *  获取请求配置
     *
     * @param displayOptions
     */
    @SuppressLint("CheckResult")
    private fun getRequestOptions(
        displayOptions: DisplayOptions,
    ) = RequestOptions().also { options ->
        //添加占位符号
        displayOptions.apply {
            options.placeholder(placeholder)//占位图
            if (width > 0 || height > 0) {
                options.override(width, height)
            }
            if (circleCrop) {
                options.circleCrop()
            }

            if (roundCorners) {
                arrayOf(topLeft, topRight, bottomLeft, bottomRight).filter { it < 0 }
                    ?.takeIf { it.isEmpty() }
                    ?.sum()
                    ?.takeIf { it > 0 }
                    .also {
                        options.transform(
                            CenterCrop(),
                            GranularRoundedCorners(topLeft, topRight, bottomRight, bottomLeft)
                        )
                    }
                if (roundingRadius > 0) {
                    options.transform(CenterCrop(), RoundedCorners(roundingRadius))
                }
            }
        }

    }


    /**
     * 设置转换效果
     *
     * @param requestBuilder 请求构建器
     * @param sourceType 目标源类型
     * @param transition 转换配置
     */
    @SuppressLint("CheckResult")
    private fun applyTransition(
        requestBuilder: RequestBuilder<out Any>,
        sourceType: SourceType,
        transition: TransitionOptions
    ) {
        kotlin.runCatching {
            transition.apply {
                if (crossFade) {
                    when (sourceType) {
                        SourceType.DRAWABLE -> {
                            (requestBuilder as RequestBuilder<Drawable>).transition(DrawableTransitionOptions().also {
                                if (this.duration > 0) {
                                    it.crossFade(this.duration)
                                } else {
                                    it.crossFade()
                                }
                            })
                        }
                        SourceType.BITMAP -> {
                            (requestBuilder as RequestBuilder<Bitmap>).transition(BitmapTransitionOptions().also {
                                if (this.duration > 0) {
                                    it.crossFade(this.duration)
                                } else {
                                    it.crossFade()
                                }
                            })
                        }
                        SourceType.GIF -> {
                        }
                        SourceType.FILE -> {
                        }
                    }
                }
            }
        }

    }

}