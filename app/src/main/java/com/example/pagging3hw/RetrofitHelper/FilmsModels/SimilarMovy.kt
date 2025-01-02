package com.example.pagging3hw.RetrofitHelper.FilmsModels

data class SimilarMovy(
    val alternativeName: String,
    val enName: String,
    val id: Int,
    val name: String,
    val poster: Poster,
    val rating: Rating,
    val type: String,
    val year: Int
)