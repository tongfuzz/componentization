package com.joyukc.store

import android.content.Context
import android.os.Parcelable
import android.util.Log
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKVHandler
import com.tencent.mmkv.MMKVLogLevel
import com.tencent.mmkv.MMKVRecoverStrategic

/**
 *
 * @author tongfu
 * @date 2022/10/28
 * @email suntongfu@joyuai.com
 * @desc SharedPreference 存储工具类
 *
 */
open interface PreferenceStore {


    companion object {

        private val store by lazy {
            PreferenceStoreImpl(MMKV.defaultMMKV())
        }

        fun init(context: Context) {
            MMKV.initialize(context)
            MMKV.registerHandler(object : MMKVHandler {
                override fun onMMKVCRCCheckFail(mmapID: String?): MMKVRecoverStrategic? {
                    return null
                }

                override fun onMMKVFileLengthError(mmapID: String?): MMKVRecoverStrategic? {
                    return null
                }

                override fun wantLogRedirecting(): Boolean {
                    return true
                }

                override fun mmkvLog(
                    level: MMKVLogLevel?,
                    file: String?,
                    line: Int,
                    function: String?,
                    message: String?
                ) {
                    //todo 更换日志框架
                    message?.apply {
                        Log.i("mmkv", this)
                    }
                }

            })
        }

        /**
         * 获取默认的PreferenceStore
         *
         * @return
         */
        fun defaultStore(): PreferenceStore {
            checkInit()
            return store
        }

        /**
         * 获取指定的PreferenceStore
         *
         * @param name  存储的名称
         * @param mode  存储模式
         * @param cryptKey 加密密钥
         * @param rootPath 存储的根目录
         * @return
         */
        fun getStore(name: String, mode: Int = 1, cryptKey: String? = null, rootPath: String? = null): PreferenceStore {
            checkInit()
            return PreferenceStoreImpl(MMKV.mmkvWithID(name, mode, cryptKey, rootPath))
        }

        /**
         * 初始化校验
         */
        private fun checkInit() {
            if (MMKV.getRootDir() == null) {
                throw IllegalStateException("You should Call PreferenceStore.init(context) first.")
            }
        }

    }


    fun put(key: String, value: Boolean): Boolean

    fun getBool(key: String): Boolean

    fun getBool(key: String, defaultValue: Boolean): Boolean

    fun put(key: String, value: Int): Boolean

    fun getInt(key: String): Int

    fun getInt(key: String, defaultValue: Int): Int

    fun put(key: String, value: Long): Boolean

    fun getLong(key: String): Long

    fun getLong(key: String, defaultValue: Long): Long

    fun put(key: String, value: Float): Boolean

    fun getFloat(key: String): Float

    fun getFloat(key: String, defaultValue: Float): Float

    fun put(key: String, value: Double): Boolean

    fun getDouble(key: String): Double

    fun getDouble(key: String, defaultValue: Double): Double

    fun put(key: String, value: String?): Boolean

    fun getString(key: String): String?

    fun getString(key: String, defaultValue: String?): String?

    fun put(key: String, value: Set<String>?): Boolean

    fun getStringSet(key: String): Set<String?>?

    fun getStringSet(key: String, defaultValue: Set<String?>?): Set<String?>?

    fun put(key: String, value: ByteArray?): Boolean

    fun getBytes(key: String): ByteArray?

    fun getBytes(key: String, defaultValue: ByteArray?): ByteArray?

    fun put(key: String, value: Parcelable?): Boolean

    fun <T : Parcelable?> getParcelable(key: String, tClass: Class<T>?): T?

    fun <T : Parcelable?> getParcelable(key: String, tClass: Class<T>?, defaultValue: T?): T?

}