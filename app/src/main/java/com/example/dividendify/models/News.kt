package com.example.dividendify.models

import com.example.dividendify.models.enums.NewsCategory

class News {
    var category = NewsCategory.GENERAL
    var datetime = 0L
    var headline = ""
    var id = 0
    var image = ""
    var related = ""
    var source = ""
    var summary = ""
    var url = ""
}