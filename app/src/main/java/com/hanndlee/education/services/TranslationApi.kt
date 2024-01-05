package com.hanndlee.education.services

import com.hanndlee.education.models.Translation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslationApi {
    @GET("get")

    suspend fun getTranslation(
       @Query("q") q: String,
       @Query("langpair") langpair: String
    ): Response<Translation>


}
