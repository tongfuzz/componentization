package com.joyukc.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.joyukc.base.delegate.anno.Level
import com.joyukc.base.delegate.ApplicationDelegate
import com.joyukc.base.delegate.IApplicationDelegate
import com.joyukc.base.tools.ActManager
import com.joyukc.base.tools.ContextContainer
import com.joyukc.log.TLog
import com.joyukc.log.TLogConfig
import com.joyukc.log.TLogManager
import com.joyukc.log.printer.TConsolePrinter
import com.joyukc.store.PreferenceStore
import java.util.prefs.Preferences

/**
 *
 * @author tongfu
 * @date 2022/11/14
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
class BaseApplication : ApplicationDelegate(), Application.ActivityLifecycleCallbacks {

    companion object {
        const val LOG_TAG = "module_base"
    }

    override fun getLevel(): Int {
        return Level.LEVEL_BASE_LIB
    }

    override fun subDelegate(): Array<Class<out IApplicationDelegate>>? {
        return null
    }

    override fun onCreateDelegate() {
        //初始化TLog日志打印
        TLogManager.init(object : TLogConfig() {}.apply {
            includeThread = true
            globalTag = LOG_TAG
            stackTraceDepth = 0
        }, arrayOf(TConsolePrinter()))
        //监听activity生命周期
        (getApplicationContextDeleagte() as Application).registerActivityLifecycleCallbacks(this)
        //创建全局Context提供对象
        ContextContainer.init(getApplicationContextDeleagte())
        //初始化SharedPreference
        PreferenceStore.init(getApplicationContextDeleagte())

    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        TLog.d("${activity.javaClass.simpleName} : onActivityCreated")
        ActManager.add(activity)
    }

    override fun onActivityStarted(activity: Activity) {
        TLog.d("${activity.javaClass.simpleName} : onActivityStarted")
    }

    override fun onActivityResumed(activity: Activity) {
        TLog.d("${activity.javaClass.simpleName} : onActivityResumed")
    }

    override fun onActivityPaused(activity: Activity) {
        TLog.d("${activity.javaClass.simpleName} : onActivityPaused")
    }

    override fun onActivityStopped(activity: Activity) {
        TLog.d("${activity.javaClass.simpleName} : onActivityStopped")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        TLog.d("${activity.javaClass.simpleName} : onActivitySaveInstanceState")
    }

    override fun onActivityDestroyed(activity: Activity) {
        TLog.d("${activity.javaClass.simpleName} : onActivityDestroyed")
        ActManager.pop(activity)
    }

}