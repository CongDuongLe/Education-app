package com.hanndlee.education.repositories

import com.hanndlee.education.models.BaseModel
import com.hanndlee.education.models.Translation
import com.hanndlee.education.services.TranslationApi

class TranslationRepoImplement(
    private val translationApi: TranslationApi

): TranslationRepo {

    override suspend fun translate(query: String, from: String, to: String): BaseModel<Translation> {
        try {
            translationApi.getTranslation(
                q = query,
                langpair = "$from|$to"
            ).also {
                return if (it.isSuccessful){
                    BaseModel.Success(data = it.body()!!)
                }else{
                    BaseModel.Error(error = it.errorBody()?.string().toString())
                }
            }
        }catch (e:Exception){
            return BaseModel.Error(error = e.message.toString())
        }
    }

}