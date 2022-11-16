package com.joyukc.pagestate.container

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.joyukc.pagestate.listener.OnRetryListener
import com.joyukc.pagestate.state.SuccessState
import com.joyukc.pagestate.view.LoadLayout

/**
 *
 * @author tongfu
 * @date 2022/11/7
 * @email suntongfu@joyuai.com
 * @desc 为view添加各种状态，本质上是将原有的view添加到framelayout中，然后用framlayout替换原有的view
 * 注意：如果原有view有一些framlayout没有的约束条件(比如ConstraintLayout的相关约束)，那么这些约束条件将不再起作用，为解决此问题，建议
 * 将原有view包裹一层framlayout
 *
 */
class ViewStateContainer : StateContainer {
    override fun suit(target: Any): Boolean {
        return target is View
    }

    override fun replaceView(target: Any, onRetryListener: OnRetryListener?): LoadLayout {
        val oldView = target as View
        val layoutParams = oldView.layoutParams
        //oldview.parent有可能为空，比如为fragment的跟view添加状态，此时view并没有父view
        val loadLayout = oldView.parent.takeIf { it is ViewGroup }
            ?.let { it as ViewGroup }
            ?.let { viewGroup ->
                val childCount = viewGroup.childCount
                var index = 0
                if (childCount > 0) {
                    index = viewGroup.children.indexOf(oldView)
                    viewGroup.removeViewAt(index)
                }
                LoadLayout(viewGroup.context, onRetryListener).also {
                    it.setSuccessState(SuccessState(oldView))
                    viewGroup.addView(it, index, layoutParams)
                }
            }
        return loadLayout ?: LoadLayout(oldView.context, onRetryListener).also {
            it.setSuccessState(SuccessState(oldView))
        }
    }
}