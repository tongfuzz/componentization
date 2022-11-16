package com.joyukc.base.extension

import android.app.Activity
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowCompat.getInsetsController
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 *
 * @author tongfu
 * @date 2022/11/16
 * @email suntongfu@joyuai.com
 * @desc 系统ui操作扩展
 *
 */


/**
 * 获取布局内衬(状态栏，导航栏)控制器
 * @param activity
 * @return
 */
private fun Activity.getInsetsController(): WindowInsetsControllerCompat? {
    return getInsetsController(window, window.findViewById(android.R.id.content))
}


/**
 * 全屏展示页面(沉浸式布局)
 *
 * @param activity
 * @param statusBarColor 状态栏颜色
 * @param navigationBarColor 导航栏颜色
 * @param showBySwipe  全屏展示时是否滑动展示状态栏
 * @param isLight 状态栏字体颜色是否是黑色 api23以上才会起作用
 */
fun Activity.fullScreen(
    @ColorInt statusBarColor: Int = Color.TRANSPARENT,
    @ColorInt navigationBarColor: Int = Color.TRANSPARENT,
    showBySwipe: Boolean = false,
    isLight: Boolean = true
) {
    window.apply {
        WindowCompat.setDecorFitsSystemWindows(this, false)
        this.statusBarColor = statusBarColor
        this.navigationBarColor = navigationBarColor
    }

    getInsetsController()?.apply {
        if (showBySwipe) {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        isAppearanceLightStatusBars = isLight
    }
}


/**
 * 隐藏状态栏
 * @param activity
 */
fun Activity.hideStatusBar() {
    getInsetsController()?.hide(WindowInsetsCompat.Type.statusBars())
}

/**
 * 展示状态栏
 * @param activity
 */
fun Activity.showStatusBar() {
    getInsetsController()?.show(WindowInsetsCompat.Type.statusBars())
}

/**
 * 隐藏导航栏
 * @param activity
 */
fun Activity.hideNavigationBar() {
    getInsetsController()?.hide(WindowInsetsCompat.Type.navigationBars())
}

/**
 * 显示导航栏
 *
 * @param activity
 */
fun Activity.showNavigationBar() {
    getInsetsController()?.show(WindowInsetsCompat.Type.navigationBars())
}

/**
 * 设置状态栏颜色
 *
 * @param activity
 * @param color
 */
fun Activity.setStatusBarColor(@ColorInt color: Int) {
    showStatusBar()
    window.statusBarColor = color
}

/**
 * 设置导航栏颜色
 *
 * @param activity
 * @param color
 */
fun Activity.setNavigationBarColor(@ColorInt color: Int) {
    showNavigationBar()
    window.navigationBarColor = color
}

/**
 * 获取状态栏高度 仅在api21及以返回高度值
 *
 * @param activity
 * @return 状态栏高度 api21以下返回0
 */
fun Activity.getStatusBarHeight(): Int {
    return ViewCompat.getRootWindowInsets(window.decorView)
        ?.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.statusBars())?.let { inset ->
            inset.top - inset.bottom
        } ?: 0
}

/**
 * 获取导航栏高度，仅在api21及以返回高度值
 *
 * @param activity
 * @return 导航栏高度 api21以下返回0
 */
fun Activity.getNavigationBarHeight(): Int {
    return ViewCompat.getRootWindowInsets(window.decorView)
        ?.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.navigationBars())?.let { inset ->
            inset.bottom - inset.top
        } ?: 0
}


/**
 * 判断状态栏是否可见，api21以下总是返回true
 *
 * @param activity
 * @return 状态栏是否可见，api21以下总是返回true
 */
fun Activity.isStatusBarVisible(): Boolean {
    return ViewCompat.getRootWindowInsets(window.decorView)?.isVisible(WindowInsetsCompat.Type.statusBars())
        ?: true
}

/**
 * 判断导航栏是否可见，api21以下总是返回true
 *
 * @param activity
 * @return 导航栏是否可见，api21以下总是返回true
 */
fun Activity.isNavigationBarVisible(): Boolean {
    return ViewCompat.getRootWindowInsets(window.decorView)?.isVisible(WindowInsetsCompat.Type.navigationBars())
        ?: true

}

/**
 * 监听软键盘的可见性及高度
 *
 * @param activity
 * @param callBack 软键盘监听回调
 */
fun Activity.registerIMEVisibilityListener(callBack: (visible: Boolean, height: Int) -> Unit) {
    ViewCompat.setOnApplyWindowInsetsListener(window.decorView, OnApplyWindowInsetsListener { _, insets ->
        val visible = insets.isVisible(WindowInsetsCompat.Type.ime())
        val height = insets.getInsets(WindowInsetsCompat.Type.ime()).let {
            it.bottom - it.top
        }
        callBack(visible, height)
        WindowInsetsCompat.CONSUMED
    })
}

