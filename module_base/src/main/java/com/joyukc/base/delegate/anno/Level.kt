package com.joyukc.base.delegate.anno

import androidx.annotation.IntDef

/**
 *
 * @author tongfu
 * @date 2022/11/14
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@IntDef(Level.LEVEL_BASE_LIB, Level.LEVEL_APP, Level.LEVEL_LIB, Level.LEVEL_BIZ)
annotation class Level(){
    companion object {
        const val LEVEL_BASE_LIB = 0
        const val LEVEL_LIB = 1
        const val LEVEL_BIZ = 2
        const val LEVEL_APP = 3
    }
}
