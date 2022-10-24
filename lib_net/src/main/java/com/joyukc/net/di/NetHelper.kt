package com.joyukc.net.di

import com.google.gson.Gson
import com.joyukc.net.calladapter.NetworkResponseCallAdapter
import com.joyukc.net.calladapter.NetworkResponseCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/**
 *
 * @author tongfu
 * @date 2022/10/21
 * @email suntongfu@joyuai.com
 * @desc  网络帮助类，使用饿汉单利模式创建，提供retrofit对象的创建
 *
 */
object NetHelper {

    private val okHttpClient by lazy {
        //todo 添加更多okhttp的配置
        OkHttpClient.Builder()
            .callTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://10.53.2.32:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseCallAdapterFactory())
            .build()
    }

    //获取不同baseUrl的retrofit对象
    fun getRetrofit(baseUrl: String) = retrofit.newBuilder()
        .baseUrl(baseUrl)
        .build()

    fun <T> getService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }


}