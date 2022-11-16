package com.joyukc.log

import com.joyukc.log.formater.StackTraceFromater
import com.joyukc.log.formater.ThreadFormater
import com.joyukc.log.parser.TLogJsonParser
import com.joyukc.log.printer.TLogPrinter

/**
 *
 * @author tongfu
 * @date 2022/10/31
 * @email suntongfu@joyuai.com
 * @desc 日志信息配置类
 *
 */
open abstract class TLogConfig {

    companion object {
        const val MAX_LEN = 3024  //打印的最大长度
        internal val THREAD_FORMATER = ThreadFormater() //线程信息格式化类
        internal val STACK_TRACE_FORMATER = StackTraceFromater() //堆栈信息格式化类
    }

    var globalTag = "TFLog"  //默认tag
    var enabled = true //是否启用
    var includeThread = false //是否打印线程信息
    var stackTraceDepth = 5 //打印堆栈深度
    var printers: List<out TLogPrinter>? = null //打印器集合
    var jsonParser: TLogJsonParser? = null //json转换


}