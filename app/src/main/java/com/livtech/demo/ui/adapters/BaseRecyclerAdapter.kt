package com.livtech.demo.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.livtech.demo.core.models.BaseItem
import com.livtech.demo.ui.viewholders.BaseViewHolder

abstract class BaseRecyclerAdapter<T : BaseItem, ViewHolder : BaseViewHolder<T>>(val items: List<T>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return getViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindData(holder, position)
    }

    open fun getItem(position: Int): T {
        return items[position]
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): ViewHolder

    open fun bindData(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}