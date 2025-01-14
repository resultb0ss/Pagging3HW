package com.example.pagging3hw.RetrofitHelper


import com.example.pagging3hw.RetrofitHelper.FilmsModels.Films
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers(
        "X-API-KEY: GHDKZKG-S2D44KC-MQXRCBW-277DXF9"
    )
    @GET("v1.4/movie?year=2023")
    suspend fun getFilms(
        @Query("limit") limit: Int = 30,
        @Query("page") page: Int,
    ): Response<Films>

}