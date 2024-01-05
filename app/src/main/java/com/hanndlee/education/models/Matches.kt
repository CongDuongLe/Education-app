package com.hanndlee.education.models

import com.google.gson.annotations.SerializedName

data class Matches(
    @SerializedName("id")
    val id: String,
    @SerializedName("segment")
    val segment: String,
    @SerializedName("translation")
    val translation: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("target")
    val target: String,
    @SerializedName("quality")
    val quality: Int,
    @SerializedName("reference")
    val reference: String?,
    @SerializedName("usage-count")
    val usageCount: Int,
    @SerializedName("subject")
    val subject: String,
    @SerializedName("created-by")
    val createdBy: String,
    @SerializedName("last-updated-by")
    val lastUpdatedBy: String,
    @SerializedName("create-date")
    val createDate: String,
    @SerializedName("last-update-date")
    val lastUpdateDate: String,
    @SerializedName("match")
    val match: Double
)

/*
{
            "id": "741452224",
            "segment": "Hello World!",
            "translation": "Ciao Mondo!",
            "source": "en-GB",
            "target": "it-IT",
            "quality": 74,
            "reference": null,
            "usage-count": 2,
            "subject": "",
            "created-by": "MateCat",
            "last-updated-by": "MateCat",
            "create-date": "2024-01-04 05:39:41",
            "last-update-date": "2024-01-04 05:39:41",
            "match": 1
        }
 */