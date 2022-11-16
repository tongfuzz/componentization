package com.joyukc.base.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.joyukc.pagestate.StateManager
import com.joyukc.pagestate.StateService
import com.joyukc.pagestate.listener.OnRetryListener
import com.joyukc.pagestate.state.BaseState

/**
 *
 * @author tongfu
 * @date 2022/11/2
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(), OnRetryListener {

    lateinit var binding: T
    lateinit var stateService: StateService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        stateService = StateManager.register(this, this)
        initPageState(stateService)
        initView()
    }

    /**
     *获取布局id
     * @return
     */
    abstract fun getLayoutId(): Int

    /**
     * 点击重试
     * @param view
     */
    override fun onRetry(view: View) {

    }

    /**
     * 初始化页面状态
     * @param stateService
     */
    open fun initPageState(stateService: StateService) {

    }

    /**
     * 展示页面状态
     * @param baseState
     */
    open fun showState(baseState: Class<out BaseState>) {
        stateService.showState(baseState)
    }

    open fun initView() {

    }

}