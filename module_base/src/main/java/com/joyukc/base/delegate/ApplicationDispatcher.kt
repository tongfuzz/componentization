package com.joyukc.base.delegate

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import java.util.*

/**
 *
 * @author tongfu
 * @date 2022/11/14
 * @email suntongfu@joyuai.com
 * @desc 应用生命周期派发类
 *
 */
@SuppressLint("StaticFieldLeak")
object ApplicationDispatcher {

    val applications = LinkedList<IApplicationDelegate>()
    lateinit var context: Application

    /**
     * 初始化
     *
     * @param context
     */
    fun init(context: Context) {
        this.context = context as Application
    }

    /**
     * 链接application
     *
     * @param applicationDelegate
     */
    fun link(applicationDelegate: IApplicationDelegate) {
        if (applications.contains(applicationDelegate)) {
            return
        }
        applications.add(applicationDelegate)
        applicationDelegate.subDelegate()?.onEach {
            kotlin.runCatching {
                if (IApplicationDelegate::class.java.isAssignableFrom(it)
                    && applications.filterIsInstance(it).isEmpty()
                ) {
                    val iApplicationDelegate = it.newInstance()
                    link(iApplicationDelegate)
                }
            }
        }
        //根据level进行排序
        val sortedList = applications.sortedBy { it.getLevel() }
        applications.clear()
        applications.addAll(sortedList)
    }

    fun performAttachBaseContext(base: Context) {
        applications.forEach {
            it.attachBaseContextDelegate(base)
        }
    }

    fun performCreate() {
        applications.forEach {
            it.onCreateDelegate()
        }
    }

    fun performConfigurationChanged(newConfig: Configuration) {
        applications.forEach {
            it.onConfigurationChangedDelegate(newConfig)
        }
    }

    fun performLowMemory() {
        applications.forEach {
            it.onLowMemoryDelegate()
        }
    }

    fun perfromTrimMemory(level: Int) {
        applications.forEach {
            it.onTrimMemoryDelegate(level)
        }
    }

    fun getApplicationContext(): Context {
        return context
    }


}