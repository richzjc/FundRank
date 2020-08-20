package com.richzjc.scrollview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.richzjc.lib.FundRankAdapter
import kotlinx.android.synthetic.main.activity_scroll.*

class ScrollDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)
        val adapter = FundRankAdapter()
        recycleView?.adapter = adapter


        val list = ArrayList<com.richzjc.lib.FundRankItemEntity>()
        (0 until 50)?.forEach {

            var itemEntity = com.richzjc.lib.FundRankItemEntity()
            itemEntity.code = "123456"
            itemEntity.name = "基金的名称是什么"
            itemEntity.founded_size = 123456.0
            itemEntity.rank = "34531"
            val otherBean = com.richzjc.lib.FundRankItemEntity.OtherBean()
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
}