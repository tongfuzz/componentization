package com.joyukc.pagestate.utils

import android.os.Looper
import com.joyukc.pagestate.container.StateContainer

/**
 *
 * @author tongfu
 * @date 2022/11/4
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
class StateUtils {

    companion object {

        fun isMainThread(): Boolean {
            return Thread.currentThread() == Looper.getMainLooper().thread
        }

        fun findContainer(target: Any, stateContainers: List<StateContainer>): StateContainer {
            return stateContainers.firstOrNull { it.suit(target) }
                ?: throw IllegalArgumentException("unspuuort add stateView for $target")
        }
    }

}