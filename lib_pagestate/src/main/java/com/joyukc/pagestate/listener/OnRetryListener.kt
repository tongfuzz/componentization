package com.joyukc.pagestate.listener

import android.view.View

/**
 *
 * @author tongfu
 * @date 2022/11/4
 * @email suntongfu@joyuai.com
 * @desc 重试回调接口
 *
 */
interface OnRetryListener {
    fun onRetry(view: View)
}