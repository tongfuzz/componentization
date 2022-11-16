package com.joyukc.pagestate

import com.joyukc.pagestate.container.ActivityStateContainer
import com.joyukc.pagestate.container.StateContainer
import com.joyukc.pagestate.container.ViewStateContainer
import com.joyukc.pagestate.listener.OnRetryListener
import com.joyukc.pagestate.utils.StateUtils

/**
 *
 * @author tongfu
 * @date 2022/11/3
 * @email suntongfu@joyuai.com
 * @desc  状态管理类，单利模式
 *
 */
object StateManager {

    private val stateContainers = mutableListOf<StateContainer>()

    init {
        stateContainers.add(ActivityStateContainer())
        stateContainers.add(ViewStateContainer())
    }

    fun register(target: Any, onRetryListener: OnRetryListener? = null): StateService {
        val stateContainer = StateUtils.findContainer(target, stateContainers)
        val loadLayout = stateContainer.replaceView(target, onRetryListener)
        return StateService(loadLayout)
    }

}