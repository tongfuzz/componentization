package com.joyukc.log.printer

import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joyukc.log.R
import com.joyukc.log.Utils

/**
 * 屏幕log显示效果控制类
 */
internal class TViewPrinterController(private val contentView: FrameLayout) {

    private var isLogShown = false

    companion object {
        const val TAG_RECYCLER_VIEW = "tag_recycler_view"
        const val TAG_CONTROLLER_VIEW = "TAG_CONTROLLER_VIEW"
    }

    fun getLogView(): RecyclerView {
        var recyView =
            contentView.findViewWithTag(TAG_RECYCLER_VIEW) ?: RecyclerView(contentView.context).apply {
                layoutManager = LinearLayoutManager(contentView.context, LinearLayoutManager.VERTICAL, false)
                tag = TAG_RECYCLER_VIEW
                background = ResourcesCompat.getDrawable(contentView.resources, R.color.color_88000000, null)
                visibility = View.GONE
                val params =
                    FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        Utils.dipToPx(contentView.context, 180f).toInt()
                    )
                params.gravity = Gravity.BOTTOM
                contentView.addView(this, params)
            }
        return recyView
    }

    private fun showLogView() {
        val logView = getLogView()
        logView.visibility = View.VISIBLE
        isLogShown = true
    }

    private fun hideLogView() {
        val logView = getLogView()
        logView.visibility = View.GONE
        isLogShown = false
    }

    private fun getControllerView(): Button {
        return contentView.findViewWithTag(TAG_CONTROLLER_VIEW) ?: Button(contentView.context).apply {
            text = "Log日志"
            setPadding(0)
            textSize = 10f
            background = ResourcesCompat.getDrawable(resources, R.drawable.shape_lt10_lb10_aa000000, null)
            setTextColor(contentView.resources.getColor(R.color.color_ffffff))
            tag = TAG_CONTROLLER_VIEW
            visibility = View.GONE
            setOnClickListener {
                if (isLogShown) {
                    hideLogView()
                } else {
                    showLogView()
                }
            }
            val params = FrameLayout.LayoutParams(
                Utils.dipToPx(contentView.context, 50f).toInt(),
                Utils.dipToPx(contentView.context, 20f).toInt()
            )
            params.gravity = Gravity.BOTTOM or Gravity.RIGHT
            params.bottomMargin = Utils.dipToPx(contentView.context, 150f).toInt()
            contentView.addView(this, params)
        }
    }

    fun showControllerView() {
        getControllerView().visibility = View.VISIBLE
    }

    fun hideControllerView() {
        getControllerView().visibility = View.GONE
    }
}
