package com.zebrostudio.movied.util

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView


class SnapHelper : LinearSnapHelper() {
    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager,
        velocityX: Int,
        velocityY: Int
    ): Int {
        val currentView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        val currentPosition = layoutManager.getPosition(currentView)
        return if (currentPosition == RecyclerView.NO_POSITION) {
            RecyclerView.NO_POSITION
        } else {
            currentPosition
        }
    }
}