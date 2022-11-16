package com.joyukc.pagestate

import com.joyukc.pagestate.state.BaseState
import com.joyukc.pagestate.view.LoadLayout

/**
 *
 * @author tongfu
 * @date 2022/11/7
 * @email suntongfu@joyuai.com
 * @desc  状态处理对象
 *
 */
class StateService(val loadLayout: LoadLayout) {

    fun addState(state: BaseState): StateService {
        loadLayout.setState(state)
        return this
    }

    fun showState(clazz: Class<out BaseState>) {
        loadLayout.showState(clazz)
    }

    fun defaultState(clazz: Class<out BaseState>) {
        loadLayout.showState(clazz)
    }

    fun getCurrentState(): Class<out BaseState>? {
        return loadLayout.currentState
    }
}