package com.example.dividendify.models

import com.example.dividendify.models.enums.MetricType

class BasicFinancialsResponse {
    var metric: BasicFinancials? = null
    var metricType = MetricType.PRICE
    var symbol = ""
}