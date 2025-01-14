package com.example.pagging3hw.RetrofitHelper

import android.util.Log
import com.example.pagging3hw.RetrofitHelper.FilmsModels.Films
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getFilms(pageSize: Int, page: Int): Response<Films> {
        return apiInterface.getFilms(pageSize, page)
    }
}