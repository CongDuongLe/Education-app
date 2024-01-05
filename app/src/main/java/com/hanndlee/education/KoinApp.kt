package com.hanndlee.education

import android.app.Application
import com.hanndlee.education.repositories.TranslationRepo
import com.hanndlee.education.repositories.TranslationRepoImplement
import com.hanndlee.education.services.TranslationApi
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KoinApp: Application() {
    override fun onCreate(){
        super.onCreate()

        val BASE_URL = "https://api.mymemory.translated.net/"

        startKoin{
            modules( module{
                // retrofit builder
                single {
                    Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                //  api
                single {
                    val retrofit : Retrofit = get()
                    retrofit.create(TranslationApi::class.java)
                }
                single {
                    TranslationRepoImplement(get())
                } bind TranslationRepo::class
            })
        }
    }
}
