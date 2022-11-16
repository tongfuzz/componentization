package com.joyukc.log

import com.joyukc.log.TLogType.TYPE
import java.lang.StringBuilder

/**
 *
 * @author tongfu
 * @date 2022/10/31
 * @email suntongfu@joyuai.com
 * @desc 日志信息打印类
 *
 */
object TLog {

    private var LOG_PACKAGE: String

    init {
        val className = TLog::class.java.name
        LOG_PACKAGE = className.substring(0, className.lastIndexOf('.'))
    }

    fun v(vararg contents: Any?) {
        log(TLogType.V, *contents)
    }

    fun vt(tag: String, vararg contents: Any?) {
        log(TLogType.V, tag, *contents)
    }

    fun d(vararg contents: Any?) {
        log(TLogType.D, *contents)
    }

    fun dt(tag: String, vararg contents: Any?) {
        log(TLogType.D, tag, *contents)
    }

    fun i(vararg contents: Any?) {
        log(TLogType.I, *contents)
    }

    fun it(tag: String, vararg contents: Any?) {
        log(TLogType.I, tag, *contents)
    }

    fun w(vararg contents: Any?) {
        log(TLogType.W, *contents)
    }

    fun wt(tag: String, vararg contents: Any?) {
        log(TLogType.W, tag, *contents)
    }

    fun e(vararg contents: Any?) {
        log(TLogType.E, *contents)
    }

    fun et(tag: String, vararg contents: Any?) {
        log(TLogType.E, tag, *contents)
    }

    fun a(vararg contents: Any?) {
        log(TLogType.A, *contents)
    }

    fun at(tag: String, vararg contents: Any?) {
        log(TLogType.A, tag, *contents)
    }


    fun log(@TYPE type: Int, vararg contents: Any?) {
        log(TLogManager.getInstance().config, type, TLogManager.getInstance().config.globalTag, *contents)
    }

    fun log(@TYPE type: Int, tag: String, vararg contents: Any?) {
        log(TLogManager.getInstance().config, type, tag, *contents)
    }

    fun log(config: TLogConfig, @TYPE type: Int, tag: String, vararg contents: Any?) {
        if (!config.enabled) {
            return
        }
        val sb = StringBuilder()
        //添加线程信息
        if (config.includeThread) {
            val threadInfo = TLogConfig.THREAD_FORMATER.format(Thread.currentThread())
            sb.append("$threadInfo \n")
        }
        //添加堆栈信息
        if (config.stackTraceDepth > 0) {
            val traceInfo =
                TLogConfig.STACK_TRACE_FORMATER.format(
                    Throwable().stackTrace.clipWithIgnore(
                        LOG_PACKAGE,
                        config.stackTraceDepth
                    )
                )
            sb.append("$traceInfo \n")
        }
        //格式化要打印的内容为json字符串
        val body = parseBody(config, *contents)
        sb.append(body)

        //循环调用打印器打印日志信息
        val printers = config.printers ?: TLogManager.getInstance().getPrinters()
        printers.forEach {
            it.print(config, type, tag, sb.toString())
        }
    }

    private fun parseBody(config: TLogConfig, vararg contents: Any?): String {

        config.jsonParser?.also {
            return it.toJson(contents.toList())
        }

        val sb = StringBuilder()
        contents.forEach {
            sb.append(it.toString()).append(";")
        }
        if (sb.isNotEmpty()) {
            sb.deleteCharAt(sb.length - 1)
        }
        return sb.toString()
    }

}