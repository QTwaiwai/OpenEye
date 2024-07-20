package com.example.module.home

import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * author : QTwawa
 * date : 2024/7/20 13:56
 */
class MyRecyclerView @JvmOverloads constructor(context: android.content.Context, attrs: AttributeSet? = null, defStyleAttr: Int=0):RecyclerView(context, attrs, defStyleAttr) {

    var isScrollEnabled = false




    override fun dispatchTouchEvent(e: MotionEvent?): Boolean {
        if (e != null) {
            when(e.action){
                MotionEvent.ACTION_DOWN->{
                    parent.requestDisallowInterceptTouchEvent(true)
                }
                MotionEvent.ACTION_MOVE->{
                  if (isScrollEnabled){
                        parent.requestDisallowInterceptTouchEvent(false)
                    }
                }
            }
        }
       return super.dispatchTouchEvent(e)
    }
}