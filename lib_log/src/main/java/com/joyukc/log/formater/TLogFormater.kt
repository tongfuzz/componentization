package com.joyukc.log.formater

/**
 *
 * @author tongfu
 * @date 2022/10/31
 * @email suntongfu@joyuai.com
 * @desc  用来格式化数据的接口
 *
 */
interface TLogFormater<T> {
    fun format(content: T): String?
}