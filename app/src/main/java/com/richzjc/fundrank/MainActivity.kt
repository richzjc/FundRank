package com.richzjc.fundrank

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addHeaderView()
        val adapter = FundRankAdapter()
        recycleView?.adapter = adapter


        val list = ArrayList<FundRankItemEntity>()
        (0 until 50)?.forEach {

            var itemEntity = FundRankItemEntity()
            itemEntity.code = "123456"
            itemEntity.name = "基金的名称是什么"
            itemEntity.founded_size = 123456.0
            itemEntity.rank = "34531"
            val otherBean = FundRankItemEntity.OtherBean()
            otherBean.one_week = 12345.0
            otherBean.one_month = 12345.0
            otherBean.three_month = 12345.0
            otherBean.six_month = 12345.0
            otherBean.one_year = 12345.0
            otherBean.two_year = 12345.0
            otherBean.three_year = 12345.0
            otherBean.five_year = 12345.0
            otherBean.ten_year = 12345.0
            otherBean.since_year = 12345.0
            otherBean.since_founded = 12345.0
            otherBean.daily = 12345.0
            otherBean.net_value = 12345.0
            itemEntity.other = otherBean
            list.add(itemEntity)
        }

        adapter?.mDatas = list
    }


    private fun addHeaderView() {
        val headerView = LayoutInflater.from(this).inflate(R.layout.fund_header_rank_not_money, recycleView, false)

        if(headerView.parent != null) {
                (headerView.parent as? ViewGroup)?.removeView(headerView)
            }

            val rightScroll = headerView?.findViewById<ViewGroup>(R.id.right_scroll)
            if (rightScroll != null && rightScroll.parent !is FundRankRecycleView.RankHorizontalScrollView) {
                val group = rightScroll.parent as ViewGroup
                val tempParams = ViewGroup.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.MATCH_PARENT)
                val scrollView = FundRankRecycleView.RankHorizontalScrollView(recycleView as FundRankRecycleView, this!!)
                group.removeView(rightScroll)
                scrollView.addView(rightScroll, tempParams)
                group.addView(scrollView, tempParams)
            }

            (rightScroll?.parent as? FundRankRecycleView.RankHorizontalScrollView)?.recycleView = recycleView as FundRankRecycleView

            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.bottomMargin = ScreenUtils.dip2px(2.5f, this)
            (headerView?.parent as? ViewGroup)?.removeView(headerView)
            ll_parent?.addView(headerView, 0, params)
        }
}