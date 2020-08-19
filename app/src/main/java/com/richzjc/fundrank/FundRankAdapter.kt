package com.richzjc.fundrank

import android.view.LayoutInflater
import android.view.View
import android.view.View.MeasureSpec
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.min

class FundRankAdapter : RecyclerView.Adapter<FundRankViewHolder>() {


    private var headerView: View? = null

    var mDatas: List<FundRankItemEntity>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FundRankViewHolder {
        createHeaderView(parent)
        val holder = FundRankViewHolder(
            LayoutInflater.from(parent?.context)
                .inflate(R.layout.fund_recycler_item_rank, parent, false)
        );
        return holder
    }

    override fun getItemCount(): Int {
        return if (mDatas != null) mDatas!!.size else 0
    }

    override fun onBindViewHolder(holder: FundRankViewHolder, position: Int) {
        updateHolderItemWith(holder)
        holder?.doBindData(mDatas!![position])
    }

    private fun updateHolderItemWith(holder: RecyclerView.ViewHolder) {
        headerView?.also {
            val group = it.findViewById<ViewGroup>(R.id.right_scroll)
            val holderGroup = holder?.itemView?.findViewById<ViewGroup>(R.id.right_scroll)
            if (group != null && holderGroup != null) {
                val size = min(group.childCount, holderGroup.childCount)
                (0 until size)?.forEach {
                    holderGroup.getChildAt(it)?.layoutParams?.width = group.getChildAt(it).measuredWidth
                }
            }
        }
    }


    private fun createHeaderView(parent: ViewGroup?) {
        if (headerView == null) {
            headerView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.fund_header_rank_not_money, parent, false)
            val widthSpec = MeasureSpec.makeMeasureSpec(1073741823, MeasureSpec.AT_MOST)
            val heightSpec = MeasureSpec.makeMeasureSpec(1073741823, MeasureSpec.AT_MOST)
            headerView?.measure(widthSpec, heightSpec)
        }
    }
}
