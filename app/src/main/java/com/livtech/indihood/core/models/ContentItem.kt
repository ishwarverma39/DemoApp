package com.livtech.indihood.core.models

import com.livtech.indihood.core.AppConstants

class ContentItem(
    val itemName: String = "",
    val itemValue: String = ""
) : BaseListItem {
    override fun getItemType(): Int {
        return AppConstants.ITEM_TYPE_CONTENT
    }
}