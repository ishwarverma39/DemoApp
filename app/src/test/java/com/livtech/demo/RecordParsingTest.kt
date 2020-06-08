package com.livtech.demo

import org.junit.Test

class RecordParsingTest {
    val schema ="{\n" +
            "  \"loan_type\": {\n" +
            "    \"type\": \"SCHEMA\",\n" +
            "    \"title\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"image\": {\n" +
            "      \"type\": \"image_type\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"Borrower Location\": {\n" +
            "      \"type\": \"address_type\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"Applicant Details\": {\n" +
            "      \"type\": \"loan_applicant_type\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"Loan Terms\": {\n" +
            "      \"type\": \"loan_terms_type\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"Repayment Schedule\": {\n" +
            "      \"type\": \"repayment_type\",\n" +
            "      \"num\": \"1+\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"address_type\": {\n" +
            "    \"type\": \"SCHEMA\",\n" +
            "    \"lat\": {\n" +
            "      \"type\": \"float\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"lng\": {\n" +
            "      \"type\": \"float\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"address\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"num\": \"1\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"image_type\": {\n" +
            "    \"type\": \"SCHEMA\",\n" +
            "    \"url\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"label\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"num\": \"1\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"repayment_type\": {\n" +
            "    \"type\": \"SCHEMA\",\n" +
            "    \"Date\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"Amount\": {\n" +
            "      \"type\": \"int\",\n" +
            "      \"num\": \"1\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"loan_terms_type\": {\n" +
            "    \"type\": \"SCHEMA\",\n" +
            "    \"Duration\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"Interest Rate\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"Loan Amount\": {\n" +
            "      \"type\": \"int\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"Loan Product\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"num\": \"1\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"loan_applicant_type\": {\n" +
            "    \"type\": \"SCHEMA\",\n" +
            "    \"Name\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"Date of Birth\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"Phone Number\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"num\": \"1+\"\n" +
            "    },\n" +
            "    \"Marital Status\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"num\": \"1\"\n" +
            "    },\n" +
            "    \"No of Dependents\": {\n" +
            "      \"type\": \"int\",\n" +
            "      \"num\": \"1\"\n" +
            "    }\n" +
            "  }\n" +
            "}"
    val records = "{\n" +
            "  \"loan1\": {\n" +
            "    \"type\": \"loan_type\",\n" +
            "    \"title\": \"Bimala Devi’s Shed Construction Loan\",\n" +
            "    \"image\": {\n" +
            "      \"url\": \"https://lh3.googleusercontent.com/4jdIr7IAp3DVx_Ss_JKAc4aKuWo5KRMUNGkhP-J60kvQ6R-zR1Tt9LbPhVASEO3iasx2X9SgmGZzmk1SDPE7vSz9hMLT\",\n" +
            "      \"label\": \"Bimala Devi (Female,47)\"\n" +
            "    },\n" +
            "    \"Borrower Location\": {\n" +
            "      \"lat\": 29.9230305,\n" +
            "      \"lng\": 78.1287824,\n" +
            "      \"address\": \"105/9 Arihant Vihar, Near Uttarakhand Public Service Commission, Haridwar, Uttarakhand\"\n" +
            "    },\n" +
            "    \"Applicant Details\": {\n" +
            "      \"Name\": \"Bimala Devi\",\n" +
            "      \"Date of Birth\": \"10th May 1959\",\n" +
            "      \"Phone Number\": [\n" +
            "        \"9999987654\"\n" +
            "      ],\n" +
            "      \"Marital Status\": \"Married\",\n" +
            "      \"No of Dependents\": 3\n" +
            "    },\n" +
            "    \"Loan Terms\": {\n" +
            "      \"Duration\": \"2 years\",\n" +
            "      \"Interest Rate\": \"12% per annum\",\n" +
            "      \"Loan Amount\": 25000,\n" +
            "      \"Loan Product\": \"Shed Construction\"\n" +
            "    },\n" +
            "    \"Repayment Schedule\": [\n" +
            "      {\n" +
            "        \"Date\": \"2nd Jan 2019\",\n" +
            "        \"Amount\": 500\n" +
            "      },\n" +
            "      {\n" +
            "        \"Date\": \"2nd Dec 2018\",\n" +
            "        \"Amount\": 505\n" +
            "      },\n" +
            "      {\n" +
            "        \"Date\": \"2nd Nov 2018\",\n" +
            "        \"Amount\": 509\n" +
            "      },\n" +
            "      {\n" +
            "        \"Date\": \"2nd Oct 2018\",\n" +
            "        \"Amount\": 514\n" +
            "      },\n" +
            "      {\n" +
            "        \"Date\": \"2nd Sept 2018\",\n" +
            "        \"Amount\": 518\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}"


    @Test
    fun parsData(){
        RecordParser(schema, records).parseDetail()
    }

}