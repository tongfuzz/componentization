package com.joyukc.log.formater

/**
 *
 * @author tongfu
 * @date 2022/10/31
 * @email suntongfu@joyuai.com
 * @desc 格式化线程信息类
 *
 */

internal class ThreadFormater : TLogFormater<Thread> {
    override fun format(thread: Thread): String? {
        return "Thread: ${thread.name}"
    }
}