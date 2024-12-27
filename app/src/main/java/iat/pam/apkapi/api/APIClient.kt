package iat.pam.apkapi.api

import iat.pam.apkapi.api.services.QuotesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private const val BASE_URL = "https://dummyjson.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val quotesService: QuotesService by lazy {
        retrofit.create(QuotesService::class.java)
    }
}
