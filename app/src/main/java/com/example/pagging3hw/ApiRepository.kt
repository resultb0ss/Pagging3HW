package com.example.pagging3hw

import com.example.pagging3hw.RetrofitHelper.ApiInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    suspend fun getFilms(pageSize: Int, page: Int) = apiInterface.getFilms(pageSize, page)
}