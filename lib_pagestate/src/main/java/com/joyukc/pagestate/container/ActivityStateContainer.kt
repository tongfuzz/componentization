package com.joyukc.pagestate.container

import android.app.Activity
import android.widget.FrameLayout
import androidx.core.view.get
import com.joyukc.pagestate.listener.OnRetryListener
import com.joyukc.pagestate.state.SuccessState
import com.joyukc.pagestate.view.LoadLayout

/**
 *
 * @author tongfu
 * @date 2022/11/4
 * @email suntongfu@joyuai.com
 * @desc 为activity添加各种状态，基本原理是将我们传入的contentview外层包裹一层framlayout，然后作为contentview
 *
 */
class ActivityStateContainer : StateContainer {
    override fun suit(target: Any): Boolean {
        return target is Activity
    }

    override fun replaceView(target: Any, onRetryListener: OnRetryListener?): LoadLayout {
        val activity = target as Activity
        val rootView = activity.findViewById<FrameLayout>(android.R.id.content)
        if (rootView.childCount == 0) {
            throw IllegalStateException("can not add state for activity before setContentView() invoked")
        }
        val index = 0
        //找到contentview
        val contentView = rootView[index]
        val layoutParams = contentView.layoutParams
        //移除contentview
        rootView.removeViewAt(index)
        return LoadLayout(activity, onRetryListener).also {
            //将contentview作为添加为Loadlayout的子view
            it.setSuccessState(SuccessState(contentView))
            //添加Loadlayout替换contentview
            rootView.addView(it, index, layoutParams)
        }
    }

}