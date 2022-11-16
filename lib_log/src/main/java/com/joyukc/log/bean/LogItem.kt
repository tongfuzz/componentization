package com.joyukc.log.bean

import com.joyukc.log.R
import com.joyukc.log.TLogType
import com.joyukc.log.Utils
import java.text.DateFormat

/**
 *
 * @author tongfu
 * @date 2022/11/1
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
data class LogItem(val time: Long, @TLogType.TYPE val level: Int, val tag: String, val content: String) {
    val color: Int
        get() {
            return when (level) {
                TLogType.V, TLogType.D, TLogType.I -> R.color.color_8bc34a
                TLogType.W -> R.color.color_ffeb3b
                TLogType.E -> R.color.color_ff0000
                TLogType.A -> R.color.color_0078ff
                else -> R.color.color_ffffff
            }
        }

    val formattedTag: String
        get() {
            return "${Utils.formatDate(time)}|$tag"
        }
}
