package com.joyukc.log

import android.content.Context
import android.util.TypedValue
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @author tongfu
 * @date 2022/11/1
 * @email suntongfu@joyuai.com
 * @desc 工具类
 *
 */
object Utils {

    /**
     * dp转px
     *
     * @param context
     * @param dpValue
     * @return
     */
    fun dipToPx(context: Context, dpValue: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.resources.displayMetrics)
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVaule
     * @return
     */
    fun spToPx(context: Context, spVaule: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, spVaule, context.resources.displayMetrics)
    }

    fun formatDate(timeMillils: Long, pattern: String, locale: Locale = Locale.CHINA): String? {
        return SimpleDateFormat(pattern, locale).format(timeMillils)
    }

    fun formatDate(timeMillils: Long): String? {
        return formatDate(timeMillils, "yy-MM-dd:HH:mm:ss")
    }
}