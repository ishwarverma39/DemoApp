package com.livtech.indihood.core

import com.livtech.indihood.core.models.Address
import com.livtech.indihood.core.models.ContentDetail
import com.livtech.indihood.core.models.ContentItem
import com.livtech.indihood.core.models.SectionItem
import org.json.JSONObject

class RecordParser(private val schema: String, private val records: String) {
    private val recordObject: JSONObject
    private val schemaObject: JSONObject

    init {
        recordObject = JSONObject(records)
        schemaObject = JSONObject(schema)
    }

    fun parseDetail(): ContentDetail {
        val contentDetail = ContentDetail("", "", "", null, null)
        var sections: ArrayList<SectionItem> = ArrayList()
        for (key in recordObject.keys()) {
            val loanObject = recordObject.getJSONObject(key)
            val type = loanObject.getString("type")
            loanObject.keys().forEach lit@{ sectionKey ->
                when (sectionKey) {
                    "type" -> return@lit
                    "title" -> contentDetail.title = loanObject.getString(sectionKey)
                    "image" -> {
                        val jsonObject = loanObject.getJSONObject(sectionKey)
                        contentDetail.imageUrl = jsonObject.getString("url")
                        contentDetail.imageLabel = jsonObject.getString("label")
                    }
                    "Borrower Location" -> {
                        val jsonObject = loanObject.getJSONObject(sectionKey)
                        contentDetail.address = (Address(
                            jsonObject.getDouble("lat"),
                            jsonObject.getDouble("lng"),
                            jsonObject.getString("address")
                        ))
                    }
                    else -> {
                        val sectionItem = getSectionItem(sectionKey, type, key)
                        sections.add(sectionItem)
                    }
                }
            }
        }
        return contentDetail.apply { this.sections = sections }
    }


    /**
     * @param typeKey is the label key
     * @param type is the type of the object
     * @param contentObject the json object
     */
    private fun getContentItem(
        typeKey: String,
        contentObject: JSONObject?, type: String, isList: Boolean
    ): ContentItem {
        return ContentItem(typeKey).apply {
            if (isList) this.itemValue = contentObject?.optJSONArray(typeKey)?.join(",")
            else this.itemValue = contentObject?.optString(typeKey)
        }
    }

    private fun getSectionItem(
        sectionKey: String,
        type: String, mainKey: String
    ): SectionItem {
        val sectionDetail = schemaObject.getJSONObject(type).getJSONObject(sectionKey)
        val sectionType = sectionDetail.getString("type")
        val contents = ArrayList<ContentItem>()
        when (true) {
            isPrimitive(sectionType) -> {
                contents.add(
                    getContentItem(
                        sectionKey,
                        recordObject.getJSONObject(sectionKey),
                        sectionType, sectionDetail.getString("num").contains("+")
                    )
                )
            }
            else -> {
                contents.addAll(
                    getContentsForCustomSchema(
                        sectionKey,
                        sectionType,
                        sectionDetail,
                        mainKey
                    )
                )
            }
        }
        return SectionItem(type, sectionKey, contents).also {
            if (it.items.size > 6) {
                it.showMore = true
                it.moreText = "SEE MORE"
                it.showList = ArrayList(it.items.subList(0, 6))
            } else it.showList = it.items
        }

    }

    private fun getContentsForCustomSchema(
        sectionKey: String,
        sectionType: String,
        sectionSchemaDetail: JSONObject, mainKey: String
    ): ArrayList<ContentItem> {
        val contentItems = ArrayList<ContentItem>()
        val sectionSchema = schemaObject.getJSONObject(sectionType)
        val isList = sectionSchemaDetail.getString("num").contains("+");
        if (isList) {
            val jsonArray = recordObject.getJSONObject(mainKey).getJSONArray(sectionKey)
            var i = 0
            while (!jsonArray.isNull(i)) {
                sectionSchema.keys().forEach {
                    if (!it.equals("type", true)) {
                        val isMultiple =
                            sectionSchema.getJSONObject(it).getString("num").contains("+")
                        contentItems.add(
                            getContentItem(
                                it,
                                jsonArray.getJSONObject(i),
                                it,
                                isMultiple
                            )
                        )
                    }
                }
                i++
            }
        } else {

        }
        return contentItems
    }

    private fun isPrimitive(type: String): Boolean {
        return when (type) {
            "string", "float", "int", "bool" -> true
            else -> false
        }
    }

    private fun isList(type: String): Boolean {
        return schemaObject.getJSONObject(type).getString("num").contains("+")
    }

    fun parseLoanDetail(): ContentDetail {
        val contentItems = ArrayList<ContentItem>().apply {
            add(ContentItem("Name", "Bimala Devi"))
            add(ContentItem())
            add(ContentItem("Date of Birth", "10th May 1959"))
            add(ContentItem("Phone Number", "9999987654"))
        }
        val sectionItems = ArrayList<SectionItem>().apply {
            add(
                SectionItem(
                    "loan_applicant_type",
                    "Applicant Details",
                    contentItems,
                    true,
                    "SEE MORE"
                )
            )
        }
        val address = Address(
            29.9230305,
            78.1287824,
            "105/9 Arihant Vihar, Near Uttarakhand Public Service Commission, Haridwar, Uttarakhand"
        )
        return ContentDetail(
            "Bimala Devi’s Shed Construction Loan",
            "https://lh3.googleusercontent.com/4jdIr7IAp3DVx_Ss_JKAc4aKuWo5KRMUNGkhP-J60kvQ6R-zR1Tt9LbPhVASEO3iasx2X9SgmGZzmk1SDPE7vSz9hMLT",
            "Bimala Devi (Female,47)",
            address,
            sectionItems
        )
    }

}