package com.zebrostudio.movied.utils

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView


class SnapHelperOneByOne : LinearSnapHelper() {
    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager,
        velocityX: Int,
        velocityY: Int
    ): Int {
        val currentView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        val currentPosition = layoutManager.getPosition(currentView)
        return if (currentPosition == RecyclerView.NO_POSITION) {
            println("no position second")
            RecyclerView.NO_POSITION
        } else {
            println("current position")
            currentPosition
        }
    }
}