package debug

import android.graphics.Color
import com.joyukc.base.extension.fullScreen
import com.joyukc.base.ui.BaseActivity
import com.joyukc.mine.R
import com.joyukc.mine.databinding.MineActivityTestBinding

/**
 *
 * @author tongfu
 * @date 2022/11/15
 * @email suntongfu@joyuai.com
 * @desc
 *
 */
class MineTestActivity : BaseActivity<MineActivityTestBinding>() {
    override fun getLayoutId() = R.layout.mine_activity_test

    override fun initView() {
        super.initView()
        fullScreen(navigationBarColor = Color.RED, isLight = true)
    }
}