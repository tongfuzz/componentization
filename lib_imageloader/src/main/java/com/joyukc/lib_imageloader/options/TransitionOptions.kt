package com.joyukc.lib_imageloader.options

/**
 *
 * @author tongfu
 * @date 2022/10/27
 * @email suntongfu@joyuai.com
 * @desc 转换效果配置类
 *
 */

/**
 * 转换效果配置
 *
 * @property crossFade 是否开启淡入淡出效果
 * @property duration 淡入淡出效果时长
 */
data class TransitionOptions(val crossFade: Boolean = true, val duration: Int = 0)