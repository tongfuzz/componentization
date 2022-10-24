package com.joyukc.net.calladapter

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

/**
 *
 * @author tongfu
 * @date 2022/10/21
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
class NetworkResponseCallAdapter<T : Any, U : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, U>
) : CallAdapter<T, NetworkResponseCall<T, U>> {

    override fun responseType(): Type {
        return successType
    }

    override fun adapt(call: Call<T>): NetworkResponseCall<T, U> {
        return NetworkResponseCall(call, errorBodyConverter)
    }
}