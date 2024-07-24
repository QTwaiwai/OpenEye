package com.example.module_video

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.LinearInterpolator



/**
 * author : QTwawa
 * date : 2024/7/21 14:42
 */
object LikeAnimation {
    fun animateLike(view: View){
        val scaleAnimX=ObjectAnimator.ofFloat(view,"scaleX",1f,1.2f,1f)
        val scaleAnimY=ObjectAnimator.ofFloat(view,"scaleY",1f,1.2f,1f)
        scaleAnimY.duration=300
        scaleAnimX.interpolator = LinearInterpolator()
        scaleAnimY.interpolator = LinearInterpolator()
        val animSet=AnimatorSet()
        animSet.apply {
            playTogether(scaleAnimX,scaleAnimY)
            start()
        }
    }
}