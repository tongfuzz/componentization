package com.joyukc.base.tools

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper

/**
 *
 * @author tongfu
 * @date 2022/11/15
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
@SuppressLint("StaticFieldLeak")
object ContextContainer {

    @SuppressLint("StaticFieldLeak")
    private var context: Context? = null
    val mainHander = Handler(Looper.getMainLooper())

    @JvmStatic
    fun init(context: Context) {
        this.context = context.applicationContext
    }

    @JvmStatic
    fun getApplicationContext(): Context {
        if (context == null) {
            throw IllegalStateException("ContextContainer is not inited yet ,you need call init(context) first in application")
        }
        return context!!
    }

}