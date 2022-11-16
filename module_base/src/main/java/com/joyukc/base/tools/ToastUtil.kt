package com.joyukc.base.tools

import android.widget.Toast
import androidx.annotation.StringRes

/**
 *
 * @author tongfu
 * @date 2022/11/15
 * @email suntongfinoyuai.com
 * @desc Toast工具类
 *
 */
class ToastUtil private constructor() {

    //公用同一个toast对象，防止多次快速点击弹出多次toast
    private var toast: Toast = Toast.makeText(ContextContainer.getApplicationContext(), "", Toast.LENGTH_SHORT)


    companion object {
        private val toastUtils by lazy {
            ToastUtil()
        }

        @JvmStatic
        fun show(text: CharSequence?, durationLong: Boolean = false) {
            text?.apply {
                ContextContainer.mainHander.post {
                    toastUtils.toast.also {
                        //使用同一个toast对象，如果不取消上一个toast会导致连续点击时出现如下异常，且toast会不显示
                        // android.view.WindowManager$BadTokenException: Unable to add window -- token
                        // android.os.BinderProxy@95f582e is not valid; is your activity running?
                        it.cancel()
                        it.setText(this)
                        it.takeIf { durationLong }?.duration = Toast.LENGTH_LONG
                    }.show()
                }
            }

        }

        @JvmStatic
        fun show(@StringRes resId: Int, durationLong: Boolean = false) {
            ContextContainer.getApplicationContext().getText(resId).apply {
                show(this, durationLong)
            }
        }
    }

}