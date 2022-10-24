package com.joyukc.net.test

import com.joyukc.net.base.ResponseBean
import com.joyukc.net.calladapter.NetworkResponse
import retrofit2.http.GET

/**
 *
 * @author tongfu
 * @date 2022/10/21
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
interface NetInterface {

    @GET("/mock/15/tongfu/category")
    suspend fun getProjects(): NetworkResponse<ResponseBean<List<Category>>, String>
}