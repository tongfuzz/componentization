package com.joyukc.net.calladapter

import java.io.IOException

/**
 *
 * @author tongfu
 * @date 2022/10/21
 * @email suntongfu@joyuai.com
 * @desc 网络请求结果类型
 *
 */
sealed class NetworkResponse<out T : Any, out U : Any> {
    //网络请求成功类型
    data class Success<T : Any>(val body: T) : NetworkResponse<T, Nothing>()

    //网络请求失败类型
    data class ApiError<U : Any>(val errorBody: U, val code: Int) : NetworkResponse<Nothing, U>()

    //网络请求异常类型
    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()

    //未知异常类型
    data class UnknownError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()
}
