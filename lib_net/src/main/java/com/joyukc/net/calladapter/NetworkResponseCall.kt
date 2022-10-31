package com.joyukc.net.calladapter

import okhttp3.MediaType
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException
import java.lang.UnsupportedOperationException

/**
 *
 * @author tongfu
 * @date 2022/10/21
 * @email suntongfu@joyuai.com
 * @desc 自定义call对象，初步解析数据，区分网络请求成功、失败、异常等情况
 *
 */
class NetworkResponseCall<T : Any, U : Any>(
    val delegateCall: Call<T>,
    val errorConverter: Converter<ResponseBody, U>
) : Call<NetworkResponse<T, U>> {
    override fun clone(): Call<NetworkResponse<T, U>> {
        return NetworkResponseCall(delegateCall.clone(), errorConverter)
    }

    override fun execute(): Response<NetworkResponse<T, U>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun enqueue(callback: Callback<NetworkResponse<T, U>>) {
        delegateCall.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                //网络请求成功
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()
                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(this@NetworkResponseCall, Response.success(NetworkResponse.Success(body)))
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(
                                NetworkResponse.UnknownError(
                                    Throwable("response is success but the responsebody is null")
                                )
                            )
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> {
                            try {
                                errorConverter.convert(error)
                            } catch (ex: Exception) {
                                null
                            }
                        }
                    }
                    if (errorBody != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.ApiError(errorBody, code))
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.UnknownError(null))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                //网络请求失败
                val networkResponse = when (t) {
                    is IOException -> NetworkResponse.NetworkError(t)
                    else -> NetworkResponse.UnknownError(t)
                }
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
            }

        })
    }

    override fun isExecuted() = delegateCall.isExecuted

    override fun cancel() {
        delegateCall.cancel()
    }

    override fun isCanceled() = delegateCall.isCanceled

    override fun request() = delegateCall.request()

    override fun timeout() = delegateCall.timeout()
}