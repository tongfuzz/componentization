package com.joyukc.net.base

/**
 *
 * @author tongfu
 * @date 2022/10/21
 * @email suntongfu@joyuai.com
 * @desc 响应结果的基类
 *
 */
data class ResponseBean<T>(
    val data: T,
    val status: Int,
    val msg: String?
)
