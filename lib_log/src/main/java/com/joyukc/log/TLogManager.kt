package com.joyukc.log

import com.joyukc.log.printer.TLogPrinter

/**
 *
 * @author tongfu
 * @date 2022/10/31
 * @email suntongfu@joyuai.com
 * @desc 日志管理类
 *
 */
class TLogManager {

    var config: TLogConfig
        private set

    private val printers = mutableListOf<TLogPrinter>()

    private constructor(config: TLogConfig, printer: Array<out TLogPrinter>) {
        this.config = config
        this.printers.addAll(printer.asList())
    }


    companion object {

        private lateinit var instance: TLogManager

        fun getInstance(): TLogManager {
            return instance
        }

        fun init(logConfig: TLogConfig, printers: Array<out TLogPrinter>) {
            instance = TLogManager(logConfig, printers)
        }
    }

    fun getPrinters(): List<out TLogPrinter> {
        return printers
    }

    fun addPrinter(printer: TLogPrinter) {
        printers.add(printer)
    }

    fun removePrinter(printer: TLogPrinter) {
        printers.remove(printer)
    }


}