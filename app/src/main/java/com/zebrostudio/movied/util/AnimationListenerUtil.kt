package com.zebrostudio.movied.util

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes

private class AnimationListener(
    private val onAnimationRepeat: () -> Unit,
    private val onAnimationStart: () -> Unit,
    private val onAnimationEnd: () -> Unit
) : Animation.AnimationListener {
    override fun onAnimationRepeat(animation: Animation) = onAnimationRepeat()
    override fun onAnimationStart(animation: Animation) = onAnimationStart()
    override fun onAnimationEnd(animation: Animation) = onAnimationEnd()
}

fun View.showAnimation(
    @AnimRes animResId: Int,
    fillAfter: Boolean = true,
    delay: Long = 0,
    onAnimationRepeat: () -> Unit = {},
    onAnimationStart: () -> Unit = {},
    onAnimationEnd: () -> Unit = {}
) = with(AnimationUtils.loadAnimation(context, animResId)) {
    startOffset = delay
    setAnimationListener(AnimationListener(onAnimationRepeat, onAnimationStart, onAnimationEnd))
    this.fillAfter = fillAfter
    startAnimation(this)
}