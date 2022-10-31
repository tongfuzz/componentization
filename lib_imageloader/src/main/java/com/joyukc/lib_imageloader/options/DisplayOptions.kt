package com.joyukc.lib_imageloader.options

import androidx.annotation.DrawableRes
import com.joyukc.lib_imageloader.R

/**
 *
 * @author tongfu
 * @date 2022/10/27
 * @email suntongfu@joyuai.com
 * @desc 图片展示配置类
 */

/**
 * 图片展示配置类
 *
 * @property placeholder  图片展示占位图
 * @property width 展示宽度
 * @property height 展示高度
 * @property circleCrop 是否展示圆形图片
 * @property roundCorners 是否展示圆角图片
 * @property roundingRadius 圆角尺寸
 * @property topLeft 左上圆角尺寸
 * @property topRight 右上圆角尺寸
 * @property bottomLeft 底部左侧圆角尺寸
 * @property bottomRight 底部右侧圆角尺寸
 */
data class DisplayOptions(
    @DrawableRes val placeholder: Int = R.drawable.imageview_default,
    val width: Int = 0,
    val height: Int = 0,
    val circleCrop: Boolean = false,
    val roundCorners: Boolean = false,
    val roundingRadius: Int = 0,
    val topLeft: Float = 0f,
    val topRight: Float = 0f,
    val bottomLeft: Float = 0f,
    val bottomRight: Float = 0f,
) {

}