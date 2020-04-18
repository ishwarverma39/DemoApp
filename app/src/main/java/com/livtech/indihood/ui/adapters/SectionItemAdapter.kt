package com.livtech.indihood.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.livtech.indihood.R
import com.livtech.indihood.core.models.SectionItem
import com.livtech.indihood.databinding.ItemSectionBinding
import com.livtech.indihood.ui.viewholders.SectionItemViewHolder

class SectionItemAdapter(items: ArrayList<SectionItem>) :
    BaseRecyclerAdapter<SectionItem, SectionItemViewHolder>(items) {
    override fun getViewHolder(parent: ViewGroup, viewType: Int): SectionItemViewHolder {
        val itemSectionBinding = DataBindingUtil.inflate<ItemSectionBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_section,
            parent,
            false
        )
        return SectionItemViewHolder(itemSectionBinding.root, itemSectionBinding)
    }
}