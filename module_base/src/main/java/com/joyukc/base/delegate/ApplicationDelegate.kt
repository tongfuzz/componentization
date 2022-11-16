package com.joyukc.base.delegate

import android.app.Application
import android.content.Context
import android.content.res.Configuration

/**
 *
 * @author tongfu
 * @date 2022/11/14
 * @email suntongfu@joyuai.com
 * @desc Application 封装类，继承 Application方便在组件单独运行是指定application，
 * 实现IApplicationDelegate， 以便在集成模式下传递Application的生命周期
 *
 */
abstract class ApplicationDelegate : Application(), IApplicationDelegate {

    override fun attachBaseContext(base: Context) {
        ApplicationDispatcher.init(this)
        ApplicationDispatcher.link(this)
        ApplicationDispatcher.performAttachBaseContext(base)
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationDispatcher.performCreate()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        ApplicationDispatcher.performConfigurationChanged(newConfig)
        super.onConfigurationChanged(newConfig)
    }

    override fun onLowMemory() {
        ApplicationDispatcher.performLowMemory()
        super.onLowMemory()
    }


    override fun onTrimMemory(level: Int) {
        ApplicationDispatcher.perfromTrimMemory(level)
        super.onTrimMemory(level)
    }

    override fun getApplicationContextDeleagte(): Context {
        return ApplicationDispatcher.getApplicationContext()
    }

}