package com.livtech.indihood.core.models

class ContentDetail(
    val title: String,
    val imageUrl: String,
    val imageLabel: String,
    val address: Address,
    val sections: ArrayList<SectionItem>
)