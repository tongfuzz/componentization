package com.joyukc.log.printer

import com.joyukc.log.TLogConfig

/**
 *
 * @author tongfu
 * @date 2022/10/31
 * @email suntongfu@joyuai.com
 * @desc  打印接口
 *
 */
interface TLogPrinter {
    /**
     * 打印日志信息
     *
     * @param config  打印配置
     * @param level 日志级别
     * @param tag 日志tag
     * @param content 日志内容
     */
    fun print(config: TLogConfig, level: Int, tag: String, content: String)
}