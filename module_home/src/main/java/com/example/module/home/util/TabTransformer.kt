package com.example.module.home.util

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max

/**
 * author : QTwawa
 * date : 2024/7/21 10:13
 */
class TabTransformer:ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        if (position < -1) {
            page.alpha = 0f
        } else if (position <= 1) {
            page.alpha = 1f
            val scale = max(0.25, (1 - abs(position.toDouble()))).toFloat()
            page.scaleX = scale
            page.scaleY = scale
            val rotationAngle = position * 180
            page.rotationY = rotationAngle
        } else {
            page.alpha = 0f
        }
    }
}