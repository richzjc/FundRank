package com.richzjc.scrollview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.core.widget.NestedScrollView

class MyNestedScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {


    override fun fling(velocityY: Int) {
        super.fling(velocityY)
        Log.i("position", "fling")
    }
}