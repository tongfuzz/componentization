package com.joyukc.pagestate.view

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.joyukc.pagestate.listener.OnRetryListener
import com.joyukc.pagestate.state.BaseState
import com.joyukc.pagestate.state.SuccessState
import com.joyukc.pagestate.utils.StateUtils

/**
 *
 * @author tongfu
 * @date 2022/11/3
 * @email suntongfu@joyuai.com
 * @desc 状态布局
 *
 */
class LoadLayout : FrameLayout {

    private val states = mutableMapOf<Class<out BaseState>, BaseState>()
    var currentState: Class<out BaseState>? = null
    private val stateIndex = 1
    private var retryListener: OnRetryListener? = null

    constructor(context: Context, retryListener: OnRetryListener? = null) : super(context) {
        this.retryListener = retryListener
    }

    //为loadlayout添加新的状态
    private fun addState(state: BaseState) {
        state.getStateName()?.apply {
            if (!states.contains(this)) {
                states[this] = state
            }
        }
    }

    //为每个状态对象添加重试监听
    fun setState(state: BaseState) {
        state.setUp(context, retryListener)
        addState(state)
    }

    //设置成功状态
    fun setSuccessState(state: SuccessState) {
        addState(state)
        state.getStateView().apply {
            visibility = View.VISIBLE
            val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            addView(this, params)
        }
        currentState = state.getStateName()
    }

    //展示某个状态
    fun showState(state: Class<out BaseState>) {
        if (StateUtils.isMainThread()) {
            showStateView(state)
        } else {
            post {
                showStateView(state)
            }
        }
    }

    //展示某个状态的view
    private fun showStateView(state: Class<out BaseState>) {
        state.takeIf { currentState != it && states.contains(it) }
            ?.also {
                currentState?.apply {
                    states[this]?.also { state ->
                        state.onDetach(state.getStateView())
                    }
                }
                if (childCount > 1) {
                    removeViewAt(stateIndex)
                }
                if (it == SuccessState::class.java) {
                    states[it]?.setVisible(true)
                } else {
                    states[it]?.also { newState ->
                        states[SuccessState::class.java]?.setVisible(newState.showSuccessState)
                        val stateView = newState.getStateView()
                        addView(stateView, stateIndex)
                        newState.onAttach(stateView)
                    }
                }
                currentState = it
            }
    }


}

