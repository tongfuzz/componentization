package com.joyukc.store

import android.os.Parcelable
import com.tencent.mmkv.MMKV

/**
 *
 * @author tongfu
 * @date 2022/10/28
 * @email suntongfu@joyuai.com
 * @desc  SharedPreference 存储实现类
 *
 */
class PreferenceStoreImpl(val mmkv: MMKV) : PreferenceStore {


    companion object {
        const val TAG = "PreferenceStore"
    }

    override fun put(key: String, value: Boolean): Boolean {
        return mmkv.encode(key, value)
    }

    override fun put(key: String, value: Int): Boolean {
        return mmkv.encode(key, value)
    }

    override fun put(key: String, value: Long): Boolean {
        return mmkv.encode(key, value)
    }

    override fun put(key: String, value: Float): Boolean {
        return mmkv.encode(key, value)
    }

    override fun put(key: String, value: Double): Boolean {
        return mmkv.encode(key, value)
    }

    override fun put(key: String, value: String?): Boolean {
        return mmkv.encode(key, value)
    }

    override fun put(key: String, value: Set<String>?): Boolean {
        return mmkv.encode(key, value)
    }

    override fun put(key: String, value: ByteArray?): Boolean {
        return mmkv.encode(key, value)
    }

    override fun put(key: String, value: Parcelable?): Boolean {
        return mmkv.encode(key, value)
    }

    override fun getBool(key: String): Boolean {
        return mmkv.decodeBool(key)
    }

    override fun getBool(key: String, defaultValue: Boolean): Boolean {
        return mmkv.decodeBool(key, defaultValue)
    }

    override fun getInt(key: String): Int {
        return mmkv.decodeInt(key)
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return mmkv.decodeInt(key, defaultValue)
    }

    override fun getLong(key: String): Long {
        return mmkv.decodeLong(key)
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return mmkv.decodeLong(key, defaultValue)
    }

    override fun getFloat(key: String): Float {
        return mmkv.decodeFloat(key)
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        return mmkv.decodeFloat(key, defaultValue)
    }

    override fun getDouble(key: String): Double {
        return mmkv.decodeDouble(key)
    }

    override fun getDouble(key: String, defaultValue: Double): Double {
        return mmkv.decodeDouble(key, defaultValue)
    }

    override fun getString(key: String): String? {
        return mmkv.decodeString(key)
    }

    override fun getString(key: String, defaultValue: String?): String? {
        return mmkv.decodeString(key, defaultValue)
    }

    override fun getStringSet(key: String): Set<String?>? {
        return mmkv.decodeStringSet(key)
    }

    override fun getStringSet(key: String, defaultValue: Set<String?>?): Set<String?>? {
        return mmkv.decodeStringSet(key, defaultValue)
    }

    override fun getBytes(key: String): ByteArray? {
        return mmkv.decodeBytes(key)
    }

    override fun getBytes(key: String, defaultValue: ByteArray?): ByteArray? {
        return mmkv.decodeBytes(key, defaultValue)
    }

    override fun <T : Parcelable?> getParcelable(key: String, tClass: Class<T>?): T? {
        return mmkv.decodeParcelable(key, tClass)
    }

    override fun <T : Parcelable?> getParcelable(key: String, tClass: Class<T>?, defaultValue: T?): T? {
        return mmkv.decodeParcelable(key, tClass, defaultValue)
    }
}