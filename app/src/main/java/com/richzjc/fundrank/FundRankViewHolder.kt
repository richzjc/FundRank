package com.richzjc.fundrank

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fund_recycler_item_rank.view.*
import kotlin.math.min

class FundRankViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun doBindData(content: FundRankItemEntity?) {
        content?.apply { itemView?.left_title?.text = leftTitle }
        val size = min(content?.rightScrollBuilder?.size
                ?: 0, itemView?.right_scroll?.childCount ?: 0)
        (0 until size)?.forEach {
            (itemView?.right_scroll?.getChildAt(it) as? TextView)?.text = content!!.rightScrollBuilder[it]
        }
    }
}