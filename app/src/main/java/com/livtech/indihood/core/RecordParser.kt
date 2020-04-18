package com.livtech.indihood.core

import com.livtech.indihood.core.models.Address
import com.livtech.indihood.core.models.ContentDetail
import com.livtech.indihood.core.models.ContentItem
import com.livtech.indihood.core.models.SectionItem

class RecordParser(val schema: String?, val records: String?) {

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