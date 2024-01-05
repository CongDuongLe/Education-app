package com.hanndlee.education.repositories

import com.hanndlee.education.models.BaseModel
import com.hanndlee.education.models.Translation

interface TranslationRepo {
    suspend fun translate(
        query: String,
        from : String,
        to : String
    ): BaseModel<Translation>
}