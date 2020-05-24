package com.zebrostudio.movied.util

import android.content.Context
import android.os.Handler
import android.os.Looper

fun Context.getOrientation() = resources.configuration.orientation;

fun withDelayOnMain(delay: Long, block: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(Runnable(block), delay)
}