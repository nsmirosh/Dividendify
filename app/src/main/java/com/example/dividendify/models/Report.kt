package com.example.dividendify.models

import com.google.gson.annotations.SerializedName

class Report {
    @SerializedName("bs")
    var balanceSheet: BalanceSheet? = null

    @SerializedName("cf")
    var cashFlow: CashFlow? = null

    @SerializedName("ic")
    var incomeStatement: IncomeStatement? = null
}