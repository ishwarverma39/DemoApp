package com.livtech.indihood.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.livtech.indihood.R
import com.livtech.indihood.core.models.ContentItem
import com.livtech.indihood.core.models.SectionItem
import com.livtech.indihood.databinding.ItemSectionBinding
import com.livtech.indihood.ui.adapters.ContentItemAdapter
import com.livtech.indihood.ui.utils.ItemDividerDecoration

class SectionItemViewHolder(itemView: View, private val itemSectionBinding: ItemSectionBinding) :
    BaseViewHolder<SectionItem>(itemView) {
    private val contentItems: ArrayList<ContentItem> = ArrayList()
    private val adapter: ContentItemAdapter

    init {
        itemSectionBinding.contentRecyclerView.layoutManager =
            GridLayoutManager(itemView.context, 2)
        adapter = ContentItemAdapter(contentItems)
        itemSectionBinding.contentRecyclerView.adapter = adapter
        itemSectionBinding.contentRecyclerView.addItemDecoration(
            ItemDividerDecoration(
                itemView.resources.getDimensionPixelOffset(
                    R.dimen.list_item_divider_height
                )
            ), 0
        )
    }

    override fun bindData(item: SectionItem) {
        itemSectionBinding.sectionItem = item
        contentItems.apply {
            clear()
            addAll(item.showMaxList)
        }
        adapter.notifyDataSetChanged()
    }
}