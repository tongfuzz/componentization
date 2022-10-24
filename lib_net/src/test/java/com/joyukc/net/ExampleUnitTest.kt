package com.joyukc.net

import com.joyukc.net.di.NetHelper
import com.joyukc.net.test.NetInterface
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    companion object {
        const val TAG = "ExampleUnitTest"
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testNetHelper() {
//        val projects = NetHelper.getService(NetInterface::class.java).getProjects()
//        assert(true)
    }
}