package com.richzjc.scrollview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import com.richzjc.lib.ScreenUtils
import kotlinx.android.synthetic.main.activity_scroll.view.*

class ScrollLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var bottomBtnView: TextView? = null

    var isPaid: Boolean = true
        set(value) {
            field = value
            addBottomBtn()
        }

    private fun addBottomBtn() {
        if (!isPaid && bottomBtnView == null) {
            bottomBtnView = LayoutInflater.from(context)
                .inflate(R.layout.sv_view_bottom, this, false) as? TextView
            val params = FrameLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.BOTTOM
            bottomBtnView?.visibility = View.GONE
            (parent?.parent as? FrameLayout)?.addView(bottomBtnView, params)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as? NestedScrollView)?.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            Log.i("position", "scrollX = ${scrollY}")
            checkTextViewIsShow(scrollY)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (!isPaid) {
            setMeasuredDimension(measuredWidth, measuredHeight + ScreenUtils.dip2px(80f, context))
        }
    }

    private fun checkTextViewIsShow(scrollY: Int) {
        bottomBtnView ?: return
        if (scrollY >= textview.bottom) {
            // 表示滑动出去了,  要显示底部的按钮
            if (bottomBtnView?.visibility != View.VISIBLE) {
                bottomBtnView?.visibility = View.VISIBLE
                AnimateUtil.showAnim(bottomBtnView)
            }
        } else {
            // 表示未滑动出去， 不显示底部的按钮
            if (bottomBtnView?.visibility == View.VISIBLE) {
                bottomBtnView?.visibility = View.GONE
                AnimateUtil.dismissAnim(bottomBtnView)
            }
        }
    }
}