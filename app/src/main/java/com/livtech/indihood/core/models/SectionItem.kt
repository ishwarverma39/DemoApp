package com.livtech.indihood.core.models

import com.livtech.indihood.core.AppConstants

class SectionItem(
    val type: String,
    val title: String,
    val items: ArrayList<ContentItem>,
    val showMore: Boolean = false,
    val moreText: String = ""

) : BaseListItem {
    override fun getItemType(): Int {
        return AppConstants.ITEM_TYPE_SECTION
    }
}