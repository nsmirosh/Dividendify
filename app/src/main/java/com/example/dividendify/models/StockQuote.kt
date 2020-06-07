package com.example.dividendify.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class StockQuote: Serializable {
    @SerializedName("c")
    var currentPrice: Double = 0.0

    @SerializedName("h")
    var highPriceToday: Double = 0.0

    @SerializedName("l")
    var lowPriceToday: Double = 0.0

    @SerializedName("o")
    var openPriceToday: Double = 0.0

    @SerializedName("pc")
    var previousClosePrice: Double = 0.0
}