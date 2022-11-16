package com.joyukc.log.printer

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joyukc.log.TLogConfig
import com.joyukc.log.TLogManager
import com.joyukc.log.Utils
import com.joyukc.log.bean.LogItem

/**
 *
 * @author tongfu
 * @date 2022/11/1
 * @email suntongfu@joyuai.com
 * @desc  屏幕打印log实现类
 *
 */
class TViewPrinter : TLogPrinter, LifecycleEventObserver {

    private val logItems = mutableListOf<LogItem>()
    private val logAdapter = LogAdapter()
    private var logView: RecyclerView?

    constructor(activity: FragmentActivity) {
        logView = activity.findViewById<FrameLayout>(android.R.id.content)?.let {
            TViewPrinterController(it).apply {
                //打印器显示隐藏控制器
                showControllerView()
            }
        }?.let {
            it.getLogView()
        }?.also {
            it.adapter = logAdapter
        }
        //监听activity生命周期，以便在activity销毁时移除打印器，防止内存泄漏
        activity.lifecycle.addObserver(this)
    }

    override fun print(config: TLogConfig, level: Int, tag: String, content: String) {
        logItems.add(LogItem(System.currentTimeMillis(), level, tag, content))
        logAdapter.submitList(logItems.toList())
        logView?.apply {
            post {
                scrollToPosition(logAdapter.itemCount)
            }
        }
    }


    class LogItemDiffCallBack : DiffUtil.ItemCallback<LogItem>() {
        override fun areItemsTheSame(oldItem: LogItem, newItem: LogItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LogItem, newItem: LogItem): Boolean {
            return oldItem == newItem
        }

    }

    class LogAdapter : ListAdapter<LogItem, LogItemVH>(LogItemDiffCallBack()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogItemVH {
            val linearLayout = LinearLayout(parent.context).also {
                it.orientation = LinearLayout.VERTICAL
            }

            val tagView = TextView(parent.context)
                .apply {
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).also {
                        linearLayout.addView(this, it)
                    }
                }

            val contentView = TextView(parent.context)
                .apply {
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).also {
                        it.leftMargin = Utils.dipToPx(context, 20f).toInt()
                        linearLayout.addView(this, it)
                    }
                }
            return LogItemVH(tagView, contentView, linearLayout)
        }

        override fun onBindViewHolder(holder: LogItemVH, position: Int) {
            holder.bind(getItem(position))
        }
    }

    class LogItemVH(
        private val tagView: TextView,
        private val contentView: TextView,
        itemView: LinearLayout
    ) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(item: LogItem) {
            val color = itemView.context.resources.getColor(item.color)
            tagView.apply {
                text = item.formattedTag
                setTextColor(color)
            }
            contentView.apply {
                text = item.content
                setTextColor(color)
            }
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

        //移除打印器
        event.takeIf { it.targetState== Lifecycle.State.DESTROYED}?.also {
            TLogManager.getInstance().removePrinter(this@TViewPrinter)
        }

    }
}