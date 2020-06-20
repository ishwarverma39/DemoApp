package com.livtech.demo.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class ItemDividerDecoration(private val dividerSize: Int?) :
    ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.adapter?.let {
            if (it.itemCount - 1 != parent.getChildAdapterPosition(view)) {
                outRect.bottom = dividerSize ?: 0
            }
        }
    }
}