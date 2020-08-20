package com.richzjc.fundrank

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.richzjc.scrollview.ScrollDemoActivity
import kotlinx.android.synthetic.main.activity_entry.*

class EntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        initListener()
    }

    private fun initListener(){
        fund_list?.setOnClickListener{
            startActivity(Intent(this@EntryActivity, MainActivity::class.java))
        }

        scroll_demo0?.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("number", 50)
            bundle.putBoolean("isPaid", true)
            val intent = Intent(this@EntryActivity, ScrollDemoActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        scroll_demo1?.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("number", 50)
            bundle.putBoolean("isPaid", false)
            val intent = Intent(this@EntryActivity, ScrollDemoActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        scroll_demo2?.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("number", 1)
            bundle.putBoolean("isPaid", false)
            val intent = Intent(this@EntryActivity, ScrollDemoActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}