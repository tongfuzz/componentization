package debug

import android.graphics.Color
import android.widget.Toast
import androidx.core.view.doOnAttach
import com.joyukc.base.extension.*
import com.joyukc.base.ui.BaseActivity
import com.joyukc.home.R
import com.joyukc.home.databinding.HomeActivityTestBinding
import com.joyukc.log.TLog
import com.joyukc.store.PreferenceStore

/**
 *
 * @author tongfu
 * @date 2022/11/14
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
class HomeTestActivity : BaseActivity<HomeActivityTestBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.home_activity_test
    }


    override fun initView() {
        super.initView()
        fullScreen()
        binding.root.doOnAttach {
            binding.root.setPadding(0, getStatusBarHeight(), 0, 0)
            TLog.d("statusbar:  visible=${isStatusBarVisible()}, height=${getStatusBarHeight()}")
        }
        //binding.root.setPadding(0, getStatusBarHeight(), 0, 0)
        binding.btnClick.setOnClickListener {
            TLog.d("点击了按钮")
            val name = PreferenceStore.defaultStore().getString("tongfu")
        }

        binding.btnClick2.setOnClickListener {
            TLog.d("点击了按钮2")
            PreferenceStore.defaultStore().put("name", "tongfu")
            val name = PreferenceStore.defaultStore().getString("name")
            TLog.d("name is $name")
            setStatusBarColor(Color.RED)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }
}