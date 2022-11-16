package com.joyukc.mine

import com.joyukc.base.BaseApplication
import com.joyukc.base.delegate.anno.Level
import com.joyukc.base.delegate.ApplicationDelegate
import com.joyukc.base.delegate.IApplicationDelegate

/**
 *
 * @author tongfu
 * @date 2022/11/15
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
class MineApplication : ApplicationDelegate() {
    override fun getLevel(): Int {
        return Level.LEVEL_BIZ
    }

    override fun subDelegate(): Array<Class<out IApplicationDelegate>>? {
        return arrayOf(BaseApplication::class.java )
    }
}