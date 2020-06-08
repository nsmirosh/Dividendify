package com.example.dividendify.models

import java.io.Serializable

class CompanyProfile : Serializable {
    var name: String? = null
    var weburl: String? = null
    var logo: String? = null
    var finnhubIndustry: String? = null
    var ticker: String? = null
}