package com.joyukc.log

import android.util.Log
import androidx.annotation.IntDef

/**
 *
 * @author tongfu
 * @date 2022/10/31
 * @email suntongfu@joyuai.com
 * @desc  日志级别
 *
 */
class TLogType {

    @IntDef(value = [V, D, I, W, E, A])
    @Retention(AnnotationRetention.SOURCE)
    annotation class TYPE

    companion object {
        const val V = Log.VERBOSE
        const val D = Log.DEBUG
        const val I = Log.INFO
        const val W = Log.WARN
        const val E = Log.ERROR
        const val A = Log.ASSERT
    }
}
