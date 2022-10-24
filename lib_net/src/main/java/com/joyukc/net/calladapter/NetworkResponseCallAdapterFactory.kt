package com.joyukc.net.calladapter

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 *
 * @author tongfu
 * @date 2022/10/21
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
class NetworkResponseCallAdapterFactory : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (Call::class.java != getRawType(returnType)) {
            //如果返回类型不是Call，说明无法创建此类型的CallAdapter 直接返回null
            return null
        }
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<NetworkResponse<<Foo>> or Call<NetworkResponse<out Foo>>"
        }
        //如果返回结果类型不是NetworkResponse,说明无法创建此类型的CallAdapter 返回null
        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != NetworkResponse::class.java) {
            return null
        }

        check(responseType is ParameterizedType) { "Response must be parameterized as NetworkResponse<Foo> or NetworkResponse<out Foo>" }

        //获取成功时的结果类型
        val successBodyType = getParameterUpperBound(0, responseType)
        //获取失败时的结果类型
        val errorBodyType = getParameterUpperBound(1, responseType)

        //查找失败类型的转换器
        val errorBodyConverter = retrofit.nextResponseBodyConverter<Any>(null, errorBodyType, annotations)

        return NetworkResponseCallAdapter<Any, Any>(successBodyType, errorBodyConverter)

    }
}