package com.livtech.indihood.core.models

import com.livtech.indihood.core.AppConstants

class SectionItem(
    var type: String?,
    var title: String,
    var items: ArrayList<ContentItem>,
    var showMore: Boolean = false,
    var moreText: String = ""
) : BaseListItem {
    lateinit var showList: ArrayList<ContentItem>
    override fun getItemType(): Int {
        return AppConstants.ITEM_TYPE_SECTION
    }

    val showMaxList: ArrayList<ContentItem>
        get() = showList
}