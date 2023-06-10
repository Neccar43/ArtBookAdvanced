package com.example.artbookadvanced.api

import com.example.artbookadvanced.model.ImageResponse
import com.example.artbookadvanced.util.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuery:String,
        @Query("key") key:String=API_KEY
    ):Response<ImageResponse>
}