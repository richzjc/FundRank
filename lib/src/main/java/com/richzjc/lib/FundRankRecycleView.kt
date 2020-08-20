package com.richzjc.lib

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.OverScroller
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import java.lang.reflect.Field
import java.lang.reflect.Method

class FundRankRecycleView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    init {
        layoutManager = LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
    }

    val scrollViews by lazy { ArrayList<RankHorizontalScrollView>() }
    var maxScrollX: Int = 0

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        val rightScroll = child?.findViewById<View>(R.id.right_scroll)
        if (rightScroll == null || rightScroll.parent is RankHorizontalScrollView) {
            super.addView(child, index, params)
        } else {
            val group = rightScroll.parent as ViewGroup
            val tempParams = ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
            val scrollView =
                RankHorizontalScrollView(
                    this,
                    context
                )
            group.removeView(rightScroll)
            scrollView.tag = child
            scrollView.addView(rightScroll, tempParams)
            group.addView(scrollView, tempParams)
            super.addView(child, index, params)
            requestData {
                do {
                    scrollView.scrollTo(maxScrollX, 0)
                    delay(20)
                } while (scrollView.scrollX < maxScrollX)
            }
        }
    }

    class RankHorizontalScrollView @JvmOverloads constructor(var recycleView: FundRankRecycleView,
                                                             context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : HorizontalScrollView(context, attrs, defStyleAttr) {

        private var moveFlag = false
        private var scrollerField: Field? = null
        private var findMethod: Method? = null
        private var downX : Float = 0f

        init {
            overScrollMode = View.OVER_SCROLL_NEVER
            scrollerField = javaClass.superclass?.getDeclaredField("mScroller")?.apply { isAccessible = true }
            initMethod()
        }

        private fun initMethod() {
            javaClass.superclass?.declaredMethods?.forEach {
                if (TextUtils.equals("findFocusableViewInMyBounds", it.name)) {
                    findMethod = it
                    findMethod?.isAccessible = true
                    return@forEach
                }
            }
        }

        override fun onAttachedToWindow() {
            super.onAttachedToWindow()
            if (!recycleView.scrollViews.contains(this))
                recycleView.scrollViews?.add(this)
            scrollTo(recycleView.maxScrollX, 0)
        }

        override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
            when (ev?.action) {
                MotionEvent.ACTION_DOWN -> moveFlag = true
                MotionEvent.ACTION_MOVE -> moveFlag = true
                else -> moveFlag = false
            }
            return super.dispatchTouchEvent(ev)
        }

        override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
            super.onScrollChanged(l, t, oldl, oldt)
            recycleView.maxScrollX = scrollX
            recycleView.scrollViews?.forEach {
                if (it != this@RankHorizontalScrollView && moveFlag)
                    it.scrollTo(recycleView.maxScrollX, 0)
            }
        }

        override fun onTouchEvent(ev: MotionEvent?): Boolean {
            if (ev?.action == MotionEvent.ACTION_DOWN) {
                (tag as? View)?.onTouchEvent(ev)
                downX = ev.x
                var mScroller: OverScroller?
                recycleView.scrollViews?.forEach {
                    mScroller = scrollerField?.get(it) as? OverScroller
                    if (mScroller != null && !mScroller!!.isFinished) {
                        mScroller?.abortAnimation()
                    }
                }
                recycleView.maxScrollX = scrollX
            } else if (ev?.action == MotionEvent.ACTION_MOVE) {
                (tag as? View)?.onTouchEvent(ev)
                if(scrollX == 0 && (ev.x > downX))
                    return false
                else if(scrollX == (getChildAt(0).width - width) && (ev.x < downX))
                    return false
            }else if(ev?.action == MotionEvent.ACTION_UP){
                if(kotlin.math.abs(ev.x - downX) < ScreenUtils.dip2px(10f, context))
                    (tag as? View)?.onTouchEvent(ev)
            }else{
                (tag as? View)?.onTouchEvent(ev)
            }
            return super.onTouchEvent(ev)
        }

        override fun fling(velocityX: Int) {
            recycleView.scrollViews.forEach {
                if (it.childCount > 0) {
                    val width: Int = it.width - it.paddingRight - it.paddingLeft
                    val right = it.getChildAt(0).width
                    val mScroller = scrollerField?.get(it) as? OverScroller
                    if (mScroller != null) {
                        mScroller.fling(it.scrollX, it.scrollY, velocityX, 0, 0,
                                Math.max(0, right - width), 0, 0, width / 2, 0)
                        val movingRight = velocityX > 0
                        val currentFocused = it.findFocus()
                        var newFocused = findMethod?.invoke(it, movingRight, mScroller.finalX, currentFocused) as? View
                        if (newFocused == null) {
                            newFocused = it
                        }
                        if (newFocused !== currentFocused) {
                            newFocused.requestFocus(if (movingRight) View.FOCUS_RIGHT else View.FOCUS_LEFT)
                        }
                        it.postInvalidateOnAnimation()
                    }
                }
            }
        }

        override fun onDetachedFromWindow() {
            super.onDetachedFromWindow()
            recycleView.scrollViews?.remove(this)
        }
    }
}


