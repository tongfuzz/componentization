package com.joyukc.log

/**
 *
 * @author tongfu
 * @date 2022/10/31
 * @email suntongfu@joyuai.com
 * @desc
 *
 */

fun Array<out StackTraceElement>.clip(deepth: Int): Array<out StackTraceElement> {
    val maxDeepth = deepth.coerceAtMost(size)
    return copyOfRange(0, maxDeepth)
}

/**
 * 剪切堆栈数据数组
 * @param ignorePackage 要忽略的数据的包名
 * @param deepth
 * @return
 */
fun Array<out StackTraceElement>.clipWithIgnore(ignorePackage: String, deepth: Int): Array<out StackTraceElement> {
    var count = 0
    forEach {
        if (it.className.startsWith(ignorePackage)) {
            count++
        }
    }
    if (count < size) {
        val maxDeepth = deepth.coerceAtMost(size - count)
        return copyOfRange(count, count + maxDeepth)
    }
    return arrayOf()
}