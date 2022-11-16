package com.joyukc.store

import android.os.Parcel
import android.os.Parcelable
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Before
    fun initStore() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        PreferenceStore.init(appContext)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.joyukc.store.test", appContext.packageName)
    }

    @Test
    fun testSPStoreString() {
        PreferenceStore.defaultStore().put("name", "tongfu")
        val name = PreferenceStore.defaultStore().getString("name")
        assertEquals("tongfu", name)
    }

    @Test
    fun testSPStoreInt() {
        PreferenceStore.defaultStore().put("age", 12)
        val age = PreferenceStore.defaultStore().getInt("age")
        assertEquals(12, age)
    }

    @Test
    fun testSPStoreBoolean() {
        PreferenceStore.defaultStore().put("isStudent", true)
        val isStudent = PreferenceStore.defaultStore().getBool("isStudent")
        assertEquals(true, isStudent)
    }

    @Test
    fun testSPStoreStringSet() {
        val friends = setOf("xiaoming", "xiaohong", "kangkang")
        PreferenceStore.defaultStore().put("friends", friends)
        val storedFriends = PreferenceStore.defaultStore().getStringSet("friends")
        assertEquals(friends, storedFriends)
    }

    @Test
    fun testSPStoreParcelable() {
        val user = User("tongfu", 12)
        PreferenceStore.defaultStore().put("user", user)
        val storedUser = PreferenceStore.defaultStore().getParcelable("user", User::class.java)
        assertEquals(user, storedUser)
    }

    data class User(val name: String, val age: Int) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readInt()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeInt(age)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<User> {
            override fun createFromParcel(parcel: Parcel): User {
                return User(parcel)
            }

            override fun newArray(size: Int): Array<User?> {
                return arrayOfNulls(size)
            }
        }
    }
}