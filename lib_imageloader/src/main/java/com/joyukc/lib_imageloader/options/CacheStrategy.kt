package com.joyukc.lib_imageloader.options

/**
 *
 * @author tongfu
 * @date 2022/10/27
 * @email suntongfu@joyuai.com
 * @desc 缓存策略
 *
 */
enum class CacheStrategy {
    ALL, //全部缓存
    NONE,//全部不缓存
    DATA,//缓存网络请求的原始数据
    RESOURCE,//仅缓存转换过后的数据
    AUTOMATIC//自动选择缓存策略
}