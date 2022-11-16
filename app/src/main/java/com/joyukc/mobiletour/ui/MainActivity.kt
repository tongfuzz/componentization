package com.joyukc.mobiletour.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.joyukc.log.TLog
import com.joyukc.log.TLogManager
import com.joyukc.log.printer.TViewPrinter
import com.joyukc.mobiletour.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TLogManager.getInstance().addPrinter(TViewPrinter(this))
        TLog.e("this is main activity")

    }

    private val rootView by lazy {

    }
}