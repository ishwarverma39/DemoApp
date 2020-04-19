package com.livtech.indihood.core.models

class ContentDetail(
    var title: String,
    var imageUrl: String,
    var imageLabel: String,
    var address: Address?,
    var sections: ArrayList<SectionItem>?
)