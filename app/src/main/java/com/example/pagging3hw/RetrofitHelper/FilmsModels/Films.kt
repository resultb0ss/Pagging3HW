package com.example.pagging3hw.RetrofitHelper.FilmsModels

data class Films(
    val docs: List<Doc>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)