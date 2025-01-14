package com.example.pagging3hw

import android.util.Log
import com.example.pagging3hw.RetrofitHelper.ApiInterface
import com.example.pagging3hw.RetrofitHelper.FilmsModels.Films
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getFilms(pageSize: Int, page: Int): Response<Films>{
        Log.d("@@@", "Отправляем запрос к API: размер страницы = $pageSize, номер страницы = $page")
        return apiInterface.getFilms(pageSize, page)
    }
}