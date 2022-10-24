package com.joyukc.net.extension

import com.joyukc.net.base.ResponseBean
import com.joyukc.net.calladapter.NetworkResponse
import com.joyukc.net.result.Result

/**
 *
 * @author tongfu
 * @date 2022/10/24
 * @email suntongfu@joyuai.com
 * @desc
 *
 */

/**
 * 转换网络请求结果，统一网络请求失败情形
 *
 * @param T
 * @param U
 * @return
 */
fun <T : Any, U : Any> NetworkResponse<ResponseBean<T>, U>.transform(): Result<T> {
    return when (this) {
        is NetworkResponse.Success<ResponseBean<T>> -> {
            val body = this.body
            if (body.msg == "success") {
                Result.Success(body.data, body)
            } else {
                //网络请求成功，服务其返回失败结果
                Result.Failure(body)
            }
        }
        is NetworkResponse.ApiError<U> -> {
            //网络请求不成功，服务器返回结果
            val errorBody = this.errorBody
            val errorCode = this.code
            Result.Error(errorCode, errorBody.toString())
        }
        is NetworkResponse.NetworkError -> {
            //网络请求失败，网络原因
            val error = this.error
            Result.Error(errorMsg = error.message)
        }
        is NetworkResponse.UnknownError -> {
            //网络请求失败，未知错误
            val error = this.error
            Result.Error(errorMsg = error?.message)
        }
    }

}