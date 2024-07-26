package com.example.module.home

import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * author : QTwawa
 * date : 2024/7/20 13:56
 */
class MyRecyclerView @JvmOverloads constructor(
    context: android.content.Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var isScrollEnabled = false
    private var setX:Float=0F
    private var setY:Float=0F


    override fun dispatchTouchEvent(e: MotionEvent): Boolean {
        val x=e.x
        val y=e.y
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                parent.requestDisallowInterceptTouchEvent(true)//让父布局不拦截事件，传到Rv来
            }
            MotionEvent.ACTION_MOVE -> {
                if (isScrollEnabled) {
                    parent.requestDisallowInterceptTouchEvent(false)
                }
                val gapX = x - setX;
                val gapY = y - setY;
                if(gapX>gapY){
                    parent.requestDisallowInterceptTouchEvent(false)
                }
            }
        }
        setX=x
        setY=y
        return super.dispatchTouchEvent(e)
    }
}
