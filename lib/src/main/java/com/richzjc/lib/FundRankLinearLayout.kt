package com.richzjc.lib

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout

class FundRankLinearLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val flag = super.dispatchTouchEvent(ev)
        when(ev?.action){
            MotionEvent.ACTION_DOWN -> isPressed = true
            MotionEvent.ACTION_MOVE -> isPressed = true
            else ->{
                isPressed = false
                postDelayed({isPressed = false}, 200)
            }
        }
        return flag
    }
}