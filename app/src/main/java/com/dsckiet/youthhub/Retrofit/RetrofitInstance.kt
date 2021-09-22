package com.dsckiet.youthhub.Retrofit

import com.example.youthhub.ApiRequest.SearchApiRequest
import com.example.youthhub.utils.constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    val api : SearchApiRequest by lazy {
        retrofit.create(SearchApiRequest::class.java)
    }

}