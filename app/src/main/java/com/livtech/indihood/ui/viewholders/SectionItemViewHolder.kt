package com.livtech.indihood.ui.viewholders

import android.view.View
import com.livtech.indihood.core.models.SectionItem
import com.livtech.indihood.databinding.ItemSectionBinding

class SectionItemViewHolder(itemView: View, private val itemSectionBinding: ItemSectionBinding) :
    BaseViewHolder<SectionItem>(itemView) {
    override fun bindData(item: SectionItem) {
        itemSectionBinding.sectionItem = item
    }
}