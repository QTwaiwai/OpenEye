package com.example.lib.base

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.MotionEventCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/27 13:53
 */
class FloatMoveView(context: Context, attrs: AttributeSet) : FloatingActionButton.Behavior() {
    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: FloatingActionButton,
        ev: MotionEvent
    ): Boolean {


        return super.onInterceptTouchEvent(parent, child, ev)

    }
}