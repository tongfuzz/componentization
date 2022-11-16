package com.joyukc.log.formater

import java.lang.StringBuilder

/**
 *
 * @author tongfu
 * @date 2022/10/31
 * @email suntongfu@joyuai.com
 * @desc  格式化堆栈信息类
 *
 */
internal class StackTraceFromater : TLogFormater<Array<out StackTraceElement?>?> {

    override fun format(traces: Array<out StackTraceElement?>?): String? {
        traces?.takeIf { it.isNotEmpty() }?.let {
            if (it.size == 1) {
                return "\t-${traces[0].toString()}"
            } else {
                val sb = StringBuilder(128)
                it.forEachIndexed { index, stackTraceElement ->
                    if (index == 0) {
                        sb.append("StackTrace: \n")
                    }
                    if (index != it.size - 1) {
                        sb.append("|-");
                        sb.append(stackTraceElement.toString())
                        sb.append("\n")
                    } else {
                        sb.append("|-")
                        sb.append(stackTraceElement.toString())
                    }
                }
                return sb.toString()
            }
        }
        return null
    }
}