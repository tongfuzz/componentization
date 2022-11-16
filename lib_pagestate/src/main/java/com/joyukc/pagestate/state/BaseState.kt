package com.joyukc.pagestate.state

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.joyukc.pagestate.listener.OnRetryListener

/**
 *
 * @author tongfu
 * @date 2022/11/3
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
abstract class BaseState {

    private var contentView: View? = null
    private var onRetryListener: OnRetryListener? = null
    private var context: Context? = null

    //在当前状态展示的时候是否展示Success状态
    open var showSuccessState = false

    protected
    fun setContentView(contentView: View) {
        this.contentView = contentView
    }

    fun setUp(context: Context, onRetryListener: OnRetryListener?) {
        this.context = context
        this.onRetryListener = onRetryListener
    }

    fun getStateView(): View {
        //首先取contentView
        contentView?.apply {
            return this
        }
        //然后获取onCreateStateView
        onCreateStateView()?.apply {
            contentView = this
        }
        //最后通过inflate方法创建布局
        if (contentView == null && getLayoutId() == 0) {
            throw IllegalStateException(
                "can not get contentView for this state," +
                        "you can specify layoutId or override onCreateStateView to provide an contentView"
            )
        }
//        val inflatedView = LayoutInflater.from(context).inflate(getLayoutId(), null)
//        contentView = inflatedView
        return (contentView ?: LayoutInflater.from(context).inflate(getLayoutId(), null)).apply {
            if (enableReload()) {
                setOnClickListener {
                    onRetryListener?.onRetry(it)
                }
            }
        }
    }

    open fun onCreateStateView(): View? {
        return null
    }

    open fun getLayoutId(): Int {
        return 0
    }

    fun getStateName(): Class<out BaseState> {
        return this.javaClass
    }

    fun setVisible(isVisible: Boolean) {
        getStateView().visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    open fun onAttach(view: View) {

    }

    open fun onDetach(view: View) {

    }

    open fun enableReload(): Boolean {
        return false
    }

}

