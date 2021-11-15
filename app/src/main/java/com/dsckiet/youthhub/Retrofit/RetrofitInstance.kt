package com.example.youthhub.Retrofit

import com.example.youthhub.ApiRequest.SearchApiRequest
import com.example.youthhub.utils.constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api =retrofit.create(SearchApiRequest::class.java)

}