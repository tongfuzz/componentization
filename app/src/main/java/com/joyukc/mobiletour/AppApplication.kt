package com.joyukc.mobiletour

import android.app.Application
import com.google.gson.Gson
import com.joyukc.log.TLogConfig
import com.joyukc.log.TLogManager
import com.joyukc.log.parser.TLogJsonParser
import com.joyukc.log.printer.TConsolePrinter

/**
 *
 * @author tongfu
 * @date 2022/10/21
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        TLogManager.init(object : TLogConfig() {}.apply {
            includeThread = true
            jsonParser = object : TLogJsonParser {
                override fun toJson(content: Any?): String {
                    return Gson().toJson(content)
                }
            }
        }, arrayOf(TConsolePrinter()))
    }

}