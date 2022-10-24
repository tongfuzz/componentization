package com.joyukc.net.test

import com.joyukc.net.calladapter.NetworkResponse
import com.joyukc.net.di.NetHelper
import com.joyukc.net.extension.transform
import kotlinx.coroutines.runBlocking
import com.joyukc.net.result.Result

/**
 *
 * @author tongfu
 * @date 2022/10/21
 * @email suntongfu@joyuai.com
 * @desc 测试文件
 *
 */


fun main() = runBlocking {
    val projects = NetHelper.getService(NetInterface::class.java).getProjects()
    val list = projects.transform()
    when (list) {
        is Result.Success<List<Category>> -> {
            println("list size is ${list.data?.size}")
        }
        is Result.Error -> {
            println("error msg is ${list.errorMsg}")
        }
        is Result.Failure -> {
            println("failure msg is ${list.body}")
        }
    }
}
