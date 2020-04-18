package com.livtech.indihood.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.livtech.indihood.R
import com.livtech.indihood.core.models.ContentItem
import com.livtech.indihood.databinding.ItemContentBinding
import com.livtech.indihood.ui.viewholders.ContentItemViewHolder

class ContentItemAdapter(items: ArrayList<ContentItem>) :
    BaseRecyclerAdapter<ContentItem, ContentItemViewHolder>(items) {
    override fun getViewHolder(parent: ViewGroup, viewType: Int): ContentItemViewHolder {
        val itemContentBinding = DataBindingUtil.inflate<ItemContentBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_content,
            parent,
            false
        )
        return ContentItemViewHolder(itemContentBinding.root, itemContentBinding)
    }
}