package com.livtech.indihood.core.models

import com.livtech.indihood.core.AppConstants

class ContentItem(
    var itemName: String = "",
    var itemValue: String? = ""
) : BaseListItem {
    override fun getItemType(): Int {
        return AppConstants.ITEM_TYPE_CONTENT
    }
}