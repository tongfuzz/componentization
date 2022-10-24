package com.joyukc.net.result

import com.joyukc.net.base.ResponseBean
import okhttp3.ResponseBody

/**
 *
 * @author tongfu
 * @date 2022/10/24
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
sealed class Result<out T> {
    data class Success<T : Any>(val data: T?, val body: ResponseBean<T>) : Result<T>()
    data class Failure<T : Any>(val body: ResponseBean<T>) : Result<T>()
    data class Error(val errorCode: Int? = 999, val errorMsg: String?) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
