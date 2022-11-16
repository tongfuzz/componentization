package com.joyukc.pagestate.container

import com.joyukc.pagestate.listener.OnRetryListener
import com.joyukc.pagestate.view.LoadLayout

/**
 *
 * @author tongfu
 * @date 2022/11/3
 * @email suntongfu@joyuai.com
 * @desc 状态承载布局
 *
 */
interface StateContainer {

    //当前对象是否可以添加状态承载布局
    fun suit(target: Any): Boolean

    //根据传入的对象创建状态承载布局
    fun replaceView(target: Any, onRetryListener: OnRetryListener? = null): LoadLayout
}