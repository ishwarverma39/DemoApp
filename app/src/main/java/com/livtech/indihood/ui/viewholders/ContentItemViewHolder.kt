package com.livtech.indihood.ui.viewholders

import android.view.View
import com.livtech.indihood.core.models.ContentItem
import com.livtech.indihood.databinding.ItemContentBinding

class ContentItemViewHolder(itemView: View, private val itemContentBinding: ItemContentBinding) :
    BaseViewHolder<ContentItem>(itemView) {

    override fun bindData(item: ContentItem) {
        itemContentBinding.contentItem = item
    }

}