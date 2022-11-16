package com.joyukc.log.parser

/**
 *
 * @author tongfu
 * @date 2022/10/31
 * @email suntongfu@joyuai.com
 * @desc  json转换接口，对外暴露接口，外部可以自定义json解析操作
 *
 */
interface TLogJsonParser {
    fun toJson(content: Any?): String
}