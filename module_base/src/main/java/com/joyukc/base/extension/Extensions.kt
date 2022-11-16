package com.joyukc.base.extension

import androidx.annotation.StringRes
import com.joyukc.base.tools.ToastUtil

/**
 *
 * @author tongfu
 * @date 2022/11/15
 * @email suntongfu@joyuai.com
 * @desc
 *
 */

fun Any.toast(msg: String, durationLong: Boolean = false) {
    ToastUtil.show(msg, durationLong)
}

fun Any.toast(@StringRes resId: Int, durationLong: Boolean = false) {
    ToastUtil.show(resId, durationLong)
}

