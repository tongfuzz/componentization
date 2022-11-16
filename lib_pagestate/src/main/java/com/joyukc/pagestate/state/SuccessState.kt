package com.joyukc.pagestate.state

import android.view.View

/**
 *
 * @author tongfu
 * @date 2022/11/4
 * @email suntongfu@joyuai.com
 * @desc  成功状态
 *
 */
class SuccessState : BaseState {


    constructor(contentView: View) : super() {
        setContentView(contentView)
    }
}