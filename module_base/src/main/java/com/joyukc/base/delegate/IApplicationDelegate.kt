package com.joyukc.base.delegate

import android.content.Context
import android.content.res.Configuration
import com.joyukc.base.delegate.anno.Level

/**
 *
 * @author tongfu
 * @date 2022/11/14
 * @email suntongfu@joyuai.com
 * @desc application生命周期代理接口
 *
 */
interface IApplicationDelegate {


    @Level
    fun getLevel(): Int

    fun subDelegate(): Array<Class<out IApplicationDelegate>>?


    fun attachBaseContextDelegate(base: Context) {
    }

    fun onCreateDelegate() {
    }

    fun onConfigurationChangedDelegate(newConfig: Configuration) {
    }

    fun onLowMemoryDelegate() {
    }

    fun onTrimMemoryDelegate(level: Int) {
    }

    fun getApplicationContextDeleagte(): Context

}