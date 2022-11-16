package com.joyukc.base.tools

import android.app.Activity
import java.util.*

/**
 *
 * @author tongfu
 * @date 2022/11/15
 * @email suntongfu@joyuai.com
 * @desc activity管理类
 *
 */
object ActManager {
    //todo 为什么要用linklist而不用arraylist
    private val activities = LinkedList<Activity>()

    /**
     * 添加activity到集合中
     * @param activity
     */
    fun add(activity: Activity?) {
        activity?.takeIf { !it.isFinishing && !activities.contains(it) }?.also {
            activities.add(it)
        }
    }

    /**
     * 移除模拟栈内顶部activity
     */
    fun pop() {
        val topActivity = activities.takeIf { it.isNotEmpty() }?.pop()
        recycler(topActivity)
    }


    /**
     * 移除模拟栈内指定的activity
     * @param activity
     */
    fun pop(activity: Activity?) {
        activity?.also {
            activities.remove(it)
            recycler(it)
        }
    }

    /**
     * 移除所有的activity
     */
    fun popAll() {
        while (activities.isNotEmpty()) {
            val activity = activities.pop()
            recycler(activity)
        }
    }

    /**
     * 回收activity
     * @param topActivity
     */
    private fun recycler(topActivity: Activity?) {
        topActivity?.takeIf { !it.isFinishing }?.finish()
    }


}