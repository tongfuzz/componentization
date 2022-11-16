package com.joyukc.mobiletour.ui

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.joyukc.lib_imageloader.ImageLoader
import com.joyukc.lib_imageloader.options.DisplayOptions
import com.joyukc.lib_imageloader.options.SourceType
import com.joyukc.lib_imageloader.options.TransitionOptions
import com.joyukc.log.TLog
import com.joyukc.log.TLogManager
import com.joyukc.log.printer.TViewPrinter
import com.joyukc.mobiletour.R
import com.joyukc.mobiletour.databinding.ActivityTestBinding
import com.joyukc.store.PreferenceStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *
 * @author tongfu
 * @date 2022/10/21
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
class AppTestActivity : AppCompatActivity() {

    companion object {
        const val TAG = "AppTestActivity"
    }

    val url =
        "https://ts1.cn.mm.bing.net/th/id/R-C.cb23d823c40653f2830b31ce9cb79dcf?rik=tqKkkE0VQ4RXpA&riu=http%3a%2f%2fwww.dnzhuti.com%2fuploads%2fallimg%2f160506%2f95-160506111G4.jpg&ehk=85GawgA15lhJED%2bdEOQZuEHgbgrDTogJCqTtzIH%2fFq8%3d&risl=&pid=ImgRaw&r=0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityTestBinding>(this, R.layout.activity_test)
        ImageLoader.with(this)
            .load(
                url,
                sourceType = SourceType.DRAWABLE,
                displayOptions = DisplayOptions(
                    roundCorners = true,
                    topLeft = 30f,
                    bottomRight = 70f
                ),
                transitionOptions = TransitionOptions(duration = 3000)
            )
            .into(binding.iv)

        PreferenceStore.init(this)
        PreferenceStore.defaultStore()
        PreferenceStore.defaultStore()
        PreferenceStore.defaultStore().put("sets", setOf("name", "tongfu"))
        val set = PreferenceStore.defaultStore().getStringSet("sets", setOf())
        println("size =${set?.size}")
        val userInfo = UserInf("haha")
        PreferenceStore.defaultStore().put("user", userInfo)
        val userInf = PreferenceStore.defaultStore().getParcelable("user", UserInf::class.java)
        println("user name is ${userInf?.name}")


        binding.btnLog.setOnClickListener {
            GlobalScope.launch {
                launch(Dispatchers.IO) {
                    TLog.v("现在时间是${System.currentTimeMillis()}")
                }
            }
//            TLog.d("现在时间是${System.currentTimeMillis()}")
//            TLog.i("现在时间是${System.currentTimeMillis()}")
//            TLog.w("现在时间是${System.currentTimeMillis()}")
//            TLog.e("现在时间是${System.currentTimeMillis()}")
//            TLog.a("现在时间是${System.currentTimeMillis()}")
            //startActivity(Intent(this, MainActivity::class.java))
        }

        TLogManager.getInstance().addPrinter(TViewPrinter(this))
        TLog.e("2022", "tongfu", "haha")

//        GlobalScope.launch {
//            ImageLoader.clearCache(this@AppTestActivity)
//            val fileAwait = async {
//                ImageLoader.with(this@AppTestActivity).downloadBitmap(url)
//            }
//            val bitmap = fileAwait.await()
//            //val bitmap = BitmapFactory.decodeFile(file.absolutePath)
//            println("$TAG------${bitmap?.width}------${bitmap?.height}")
//            launch(Dispatchers.Main) {
//                binding.iv.setImageBitmap(bitmap)
//            }
//        }

    }

    class UserInf(
        var name: String? = "tongfu"
    ) : Parcelable {
        constructor(parcel: Parcel) : this(parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<UserInf> {
            override fun createFromParcel(parcel: Parcel): UserInf {
                return UserInf(parcel)
            }

            override fun newArray(size: Int): Array<UserInf?> {
                return arrayOfNulls(size)
            }
        }
    }

}