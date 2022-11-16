package com.joyukc.log.printer

import android.util.Log
import com.joyukc.log.TLogConfig
import com.joyukc.log.TLogConfig.Companion.MAX_LEN

/**
 *
 * @author tongfu
 * @date 2022/10/31
 * @email suntongfu@joyuai.com
 * @desc 控制台打印
 *
 */
class TConsolePrinter : TLogPrinter {
    override fun print(config: TLogConfig, level: Int, tag: String, content: String) {
        val lineNumbers = content.length / MAX_LEN
        if (lineNumbers > 0) {
            var index = 0
            for (i in 0 until lineNumbers) {
                Log.println(level, tag, content.substring(index, index + MAX_LEN))
                index += MAX_LEN
            }
            if (index < content.length) {
                Log.println(level, tag, content.substring(index, content.length))
            }
        } else {
            Log.println(level, tag, content)
        }
    }
}