package iat.pam.apkapi.api.services

import iat.pam.apkapi.api.model.QuotesResponse
import retrofit2.Call
import retrofit2.http.GET

interface QuotesService {

    @GET("quotes")
    fun getAll(): Call<QuotesResponse>
}