package com.hanndlee.education.models

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("translatedText")
    val translatedText: String,

    @SerializedName("match")
    val match: Double

)