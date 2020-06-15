package com.example.dividendify.models

import com.google.gson.annotations.SerializedName

class BasicFinancials {
    @SerializedName("10DayAverageTradingVolume")
    var tenDayAverageTradingVolume = 0.00

    @SerializedName("13WeekPriceReturnDaily")
    var thirteenWeekPriceReturnDaily = 0.00

    @SerializedName("26WeekPriceReturnDaily")
    var twentySixPriceReturnDaily = 0.00

    @SerializedName("3MonthAverageTradingVolume")
    var threeMonthAverageTradingVolume = 0.00

    @SerializedName("52WeekHigh")
    var fiftyTwoWeekHigh = 0.00

    @SerializedName("52WeekHighDate")
    var fiftyTwoWeekHighDate = ""

    @SerializedName("52WeekLow")
    var fiftyTwoWeekLow = 0.00

    @SerializedName("52WeekLowDate")
    var fiftyTwoWeekLowDate = ""

    @SerializedName("52WeekPriceReturnDaily")
    var fiftyTwoWeekPriceReturnDaily = 0.00

    @SerializedName("5DayPriceReturnDaily")
    var fiveDayPriceReturnDaily = 0.00
    var beta = 0.00
    var marketCapitalization = 0.00
}